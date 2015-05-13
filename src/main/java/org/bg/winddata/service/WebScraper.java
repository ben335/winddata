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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Transactional(rollbackFor = HibernateException.class)
public abstract class WebScraper {

    final static Logger logger = Logger.getLogger(WebScraper.class);

    public String url = null;
    public String fileLocation = null;
    public DaoFactory daoFactory;
    public int stationId;

    public WebScraper(String url, DaoFactory daoFactory, int stationId) throws IOException {
        this.url = url;
        this.daoFactory = daoFactory;
        this.stationId = stationId;
        logger.info("Loaded constructor with dao and url: " + url);

        getReadingsFromWeb();
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) { this.url = url; }

    public String getFileLocation() { return fileLocation; }

    public void setFileLocation(String fileLocation) { this.fileLocation = fileLocation; }

    public int getStationId() { return stationId; }

    public void setStationId(int stationId) { this.stationId = stationId; }


    @Scheduled(cron="0 0/5 * * * ?")
    public void getReadingsFromWeb() throws IOException {
        logger.info("Getting Readings from website: " + url);
        logger.info("Current time is :: " + new Date());
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

    public Document parseWebPage(String url) throws IOException {
        DynamicContentScraper dynamicContentScraper = new DynamicContentScraper();
        String html = dynamicContentScraper.getDynamicContentFromUrl(url);
        Document doc = Jsoup.parse(html);
        return doc;
    }

    abstract public Elements parseElements(Document doc);

    abstract public ArrayList<Reading> parseRows(Elements rows);
}