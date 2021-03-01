package com.xiaomai.cloud.rocket.batch;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * 批量消息发送的分割工具类
 *
 * @author Madison
 * @date 2021/3/1
 */
public class ListSplitter implements  Iterator<List<Message>>{
    private final int SIZE_LIMIT = 1024 *1024*4;
    private final List<Message> messages;
    private int currIndex;
    public ListSplitter(List<Message> messages){
        this.messages=messages;
    }

    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int nextIndex = currIndex;
        int totalSize = 0;
        for (;nextIndex<messages.size();nextIndex++){
            Message message = messages.get(nextIndex);
            int tmpSize = message.getTopic().length()+message.getBody().length;

            Map<String,String> properties = message.getProperties();
            for(Map.Entry<String,String> entry : properties.entrySet()){
                tmpSize+=entry.getKey().length()+entry.getValue().length();
            }
            tmpSize = tmpSize+ 20; //增加日志开销20字节

            if(tmpSize>SIZE_LIMIT){
                //单个 消息超过最大限制  忽略，否则会阻塞分裂进程
                if(nextIndex-currIndex ==0){
                    //如果下一个子列表没有元素，则添加这个字列表然后退出循环，否则只推出循环
                    nextIndex++;
                }
                break;
            }

            if(tmpSize+totalSize>SIZE_LIMIT){
                break;
            }else{
                totalSize+=totalSize;
            }
         }
         List<Message> subList  = messages.subList(currIndex,nextIndex);
        currIndex =nextIndex;
        return subList;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super List<Message>> action) {

    }
}
