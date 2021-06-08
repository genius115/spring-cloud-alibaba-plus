package com.xiaomai;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.n3r.idworker.Sid;
import org.n3r.idworker.WorkerIdStrategy;

/**
 * @author Madison
 * @date 2021/6/8
 */
@Slf4j
public class SnowflakeTest {
    @Test
    public void test1(){
        WorkerIdStrategy wis = new WorkerIdStrategy() {
            @Override
            public void initialize() {

            }

            @Override
            public long availableWorkerId() {
                return 0;
            }

            @Override
            public void release() {

            }
        };
        log.warn("idworker-client 生成主键：{}",Sid.nextShort());
        log.info("idworker-client生成主键：{}",Sid.next());
    }
}
