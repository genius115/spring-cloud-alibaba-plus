package com.xiaomai.xiaomaispringbootinit.elasticjob;


import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @author Madison
 * @date 2021/7/8
 */
@Slf4j
public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                log.info("do something "+context.getShardingItem());
                break;
            case 1:
                // do something by sharding item 1
                log.info("do something "+context.getShardingItem());
                break;
            case 2:
                // do something by sharding item 2
                log.info("do something "+context.getShardingItem());
                break;
            // case n: ...
            default:
                log.info("do something "+context.getShardingItem());
                break;
        }
    }
}