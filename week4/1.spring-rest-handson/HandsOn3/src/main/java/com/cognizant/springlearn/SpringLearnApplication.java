package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat sdf = context.getBean("dateFormat", SimpleDateFormat.class);
        try {
            Date date = sdf.parse("31/12/2018");
            LOGGER.debug("Parsed date: {}", date.toString());
        } catch (Exception e) {
            LOGGER.error("Parsing error", e);
        }
    }
}
