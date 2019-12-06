package org.sbybfai.ureport.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UreportDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(UreportDemoApplication.class);

    /**
     * jar启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(UreportDemoApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        LOGGER.info("The ureport demo application has been started successfully!");
    }
}
