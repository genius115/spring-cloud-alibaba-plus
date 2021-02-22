package com.xiaomai.cloud.service.serialnumber;

import com.xiaomai.cloud.mapper.serialnumber.GroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Madison
 * @date 2021/2/1
 */
@Service
public class GroupServiceImpl implements IGroupService {

    Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupMapper groupMapper;

    //静态变量存储最大值
    private static final AtomicInteger atomicNum = new AtomicInteger();
    //初始化分组编号
    private final int INIT_GROUP_NUM = 0;

    /**
     *
     *  @Author javaloveiphone
     *  @Description :初始化设置分组编号最大值
     *  @throws Exception
     *  void
     */
    @PostConstruct
    public void initMaxNum() {
        try {
            int maxGroupNum = groupMapper.getMaxGroupNum();
            if (maxGroupNum < INIT_GROUP_NUM) {
                maxGroupNum = INIT_GROUP_NUM;
            }
            if (logger.isDebugEnabled()) {
                logger.debug("初始化分组编号最大值为：" + maxGroupNum);
            }
            atomicNum.set(maxGroupNum);
        } catch (Exception e) {
            logger.error("初始化获取分组编号最大值异常", e);
        }
    }

    /**
     *
     * @Author javaloveiphone
     * @Description :获取最新分组编号
     * @return
     * int
     * 注：此方法并没有使用synchronized进行同步，因为共享的编号自增操作是原子操作，线程安全的
     *
     */
    @Override
    public String getNewAutoNum() {
        //线程安全的原子操作，所以此方法无需同步
        int newNum = atomicNum.incrementAndGet();
        //数字长度为5位，长度不够数字前面补0
        String newStrNum = String.format("%05d", newNum);
        return newStrNum;
    }
}