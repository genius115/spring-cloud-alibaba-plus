import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.ApiConfig;
import org.junit.Test;

/**
 * @author Madison
 * @date 2021/4/16
 */
public class smartdocTest {
    public static void main(String[] args) {

    }

    /**
     * 包括设置请求头，缺失注释的字段批量在文档生成期使用定义好的注释
     */
    @Test
    public void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:55510/");
        //true会严格要求注释，推荐设置true
        config.setStrict(true);
        //true会将文档合并导出到一个markdown
        //config.setAllInOne(true);
        //生成html时加密文档名不暴露controller的名称
        config.setMd5EncryptedHtmlName(true);

        //指定文档输出路径
        //@since 1.7 版本开始，选择生成静态html doc文档可使用该路径：DocGlobalConstants.HTML_DOC_OUT_PATH;
        config.setOutPath("D:\\test\\smartdoc");
        // @since 1.2,如果不配置该选项，则默认匹配全部的controller,
        // 如果需要配置有多个controller可以使用逗号隔开
        config.setPackageFilters("com.xiaomai.springboot.controller");


        long start = System.currentTimeMillis();
        //获取接口数据后自行处理
        ApiDocBuilder.buildApiDoc(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
}
