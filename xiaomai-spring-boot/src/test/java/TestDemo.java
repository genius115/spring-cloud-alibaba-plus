import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Madison
 * @date 2021/7/1
 */
@Slf4j
public class TestDemo {

    //Guava包 
    @Test
    public void test8(){
        //变量小写连接线转小写驼峰
        log.info(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "user-name"));
        log.info(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, "user-name"));
        log.info(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "user_name"));
        log.info(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "user_name"));
        log.info(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, "userName"));
        log.info(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "userName"));
        log.info(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "userName"));
    }
}
