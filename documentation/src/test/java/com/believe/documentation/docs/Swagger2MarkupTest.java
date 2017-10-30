package com.believe.documentation.docs;

import com.believe.documentation.ApiDocsApplication;
import com.believe.documentation.SwaggerConfiguration;
import com.believe.documentation.SwaggerResultHandlerUtf8;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApiDocsApplication.class, SwaggerConfiguration.class})
public class Swagger2MarkupTest {

  private static final Logger log = LoggerFactory.getLogger(Swagger2MarkupTest.class);

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Rule
  public final JUnitRestDocumentation restDocumentation =
    new JUnitRestDocumentation(System.getProperty("io.springfox.staticdocs.snippetsOutputDir"));

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
      .apply(documentationConfiguration(this.restDocumentation))
      .build();
  }

  @Test
  public void createSpringfoxSwaggerJson() throws Exception {
    //String designFirstSwaggerLocation = Swagger2MarkupTest.class.getResource("/swagger.yaml").getPath();

    String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");
    MvcResult mvcResult = this.mockMvc.perform(get("/v2/api-docs")
      .accept(MediaType.APPLICATION_JSON))
      .andDo(SwaggerResultHandlerUtf8.outputDirectory(outputDir).build())
      .andExpect(status().isOk())
      .andReturn();

    //String springfoxSwaggerJson = mvcResult.getResponse().getContentAsString();
    //SwaggerAssertions.assertThat(Swagger20Parser.parse(springfoxSwaggerJson)).isEqualTo(designFirstSwaggerLocation);
  }
}
