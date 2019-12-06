package org.sbybfai.ureport.demo.config;

import org.sbybfai.ureport.console.UReportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.Servlet;

@Configuration
@ImportResource("classpath:ureport-console-context.xml")
public class UReport2Config {

    @Bean
    public ServletRegistrationBean<Servlet> buildUReportServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean<Servlet>(new UReportServlet(), "/ureport/*");
        return bean;
    }
}
