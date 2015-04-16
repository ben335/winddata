package org.bg.winddata;

import org.bg.winddata.domain.Reading;
import org.bg.winddata.util.ObjectUtility;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

public class WindDataRunner {
    public static void main(String[] args) throws IOException {

        ArrayList<Reading> listOfReadings = new ArrayList();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");

        WebScraper webScraper = (WebScraper) applicationContext.getBean("webScraper");



        listOfReadings = webScraper.getReadingsFromWeb(listOfReadings);
        //listOfReadings = webScraper.getReadingsFromFile(listOfReadings);
        ObjectUtility.printAllReadings(listOfReadings);
    }

}
