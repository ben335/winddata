package org.bg.winddata.service;

import org.apache.log4j.Logger;
import org.bg.winddata.DaoFactory;
import org.bg.winddata.DynamicContentScraper;
import org.bg.winddata.domain.Reading;
import org.bg.winddata.util.ObjectUtility;
import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Transactional(rollbackFor = HibernateException.class)
public abstract class WebScraper {

    final static Logger logger = Logger.getLogger(WebScraper.class);

    public String url = null;
    public String fileLocation = null;
    public DaoFactory daoFactory;

    public WebScraper() {
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void getReadingsFromWeb() throws IOException {
        logger.info("Getting Readings from website: " + url);
        Document doc = parseWebPage(url);
        Elements rows = parseElements(doc);
        ArrayList<Reading> listOfReadings = new ArrayList();
        listOfReadings = parseRows(rows);
        Collections.reverse(listOfReadings);
        //ObjectUtility.printAllReadings(listOfReadings);
        for (Reading reading: listOfReadings){
            if (!this.daoFactory.getReadingDao().existsByDateTime(reading)){
                try {
                    this.daoFactory.getReadingDao().addReading(reading);
                    logger.info("Added record to database: " + ObjectUtility.printReading(reading));

                } catch (RuntimeException e){
                    logger.error("Exception when storing to the database: ", e);
                    throw e;
                }
            }
        }
    }

    public ArrayList<Reading> getReadingsFromFile(ArrayList<Reading> listOfReadings) throws IOException {
        Document doc = parsePage(fileLocation);
        Elements rows = parseElements(doc);
        listOfReadings = parseRows(rows);
        return listOfReadings;
    }

    public Document parsePage(String fileLocation) throws IOException {
        File input = new File(fileLocation);
        Document doc = Jsoup.parse(input, "UTF-8", "");
        return doc;
    }

    public Document parseWebPage(String url) throws IOException {
        DynamicContentScraper dynamicContentScraper = new DynamicContentScraper();
        String html = dynamicContentScraper.getDynamicContentFromUrl(url);
        Document doc = Jsoup.parse(html);
        return doc;
    }

    abstract public Elements parseElements(Document doc);

    abstract public ArrayList<Reading> parseRows(Elements rows);
}