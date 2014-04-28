package com.springapp.mvc;

import com.framework.util.dom.DomHandler;
import com.framework.util.mail.SimpleMailSender;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(JUnit4.class)
/*@WebAppConfiguration
@ContextConfiguration("classpath:applicationContext.xml")*/
public class AppTests {

    private MockMvc mockMvc;

    private SimpleMailSender simpleMailSender;

    /*@SuppressWarnings("SpringJavaAutowiringInspection")*/
    /*protected WebApplicationContext wac;*/

    @Ignore
    public void setup() {
        /*this.mockMvc = webAppContextSetup(this.wac).build();*/
        /*this.simpleMailSender = new SimpleMailSender();*/
    }

    @Ignore
    public void simple() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Ignore
    public void LoadConfig() {
        simpleMailSender.loadConfigFromPropertiesFile("mail.properties");
    }

    @Ignore
    public void obtainDom() {
        DomHandler domHandler = new DomHandler();
        String htmlContent = domHandler.getContent("http://v.youku.com/v_show/id_XNjcyMzg4OTQw.html?f=21915175&ev=2");
        System.out.print(domHandler.getSubStringFromStrToStr(htmlContent, "<body", "</body>"));
    }
}
