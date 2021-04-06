package com.swagger2markup;

import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;

/**
 *几个关键内容：
 *  MarkupLanguage.ASCIIDOC：指定了要输出的最终格式。除了ASCIIDOC之外，还有MARKDOWN和CONFLUENCE_MARKUP
 *  from(new URL("http://localhost:8080/v2/api-docs")：指定了生成静态部署文档的源头配置，可以是这样的URL形式，也可以是符合Swagger规范的String类型或者从文件中读取的流。如果是对当前使用的Swagger项目，我们通过使用访问本地Swagger接口的方式，如果是从外部获取的Swagger文档配置文件，就可以通过字符串或读文件的方式
 *  toFolder(Paths.get("src/docs/asciidoc/generated")：指定最终生成文件的具体目录位置
 *
 *
 * @author Madison
 * @date 2021/3/31
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Swagger2MarkupTest {

    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:10013/v2/api-docs?group=%E5%AD%A6%E7%94%9F%E7%AE%A1%E7%90%86"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("src/docs/asciidoc/generated"));
        System.out.println("接口生成Ascii文档成功！");
    }

    @Test
    public void generateMarkdownDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:10013/v2/api-docs?group=%E5%AD%A6%E7%94%9F%E7%AE%A1%E7%90%86"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("src/docs/markdown/generated/all"));
//单一文件              .toFolder(Paths.get("src/docs/markdown/generated"));
        System.out.println("接口生成Markdown文档成功！");
    }

    @Test
    public void generateConfluenceDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:10013/v2/api-docs?group=%E5%AD%A6%E7%94%9F%E7%AE%A1%E7%90%86"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("src/docs/confluence/generated"));
        System.out.println("接口生成Confluence文档成功！");
    }
}
