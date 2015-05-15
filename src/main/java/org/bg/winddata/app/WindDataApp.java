package org.bg.winddata.app;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;



public class WindDataApp {
    final static Logger logger = Logger.getLogger(WindDataApp.class);

    public static void main(String[] args) throws IOException {

        logger.info("WINDDATA APPLICATION STARTING");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/db-context.xml");

    }

}
