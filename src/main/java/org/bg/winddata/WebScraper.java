package org.bg.winddata;

import org.bg.winddata.domain.Reading;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class WebScraper {

    ArrayList<Reading> listOfReadings = new ArrayList();
    public String url = null;
    public String fileLocation = null;

    public WebScraper() {
    }

    public WebScraper(ArrayList<Reading> listOfReadings, String url, String fileLocation) {
        this.listOfReadings = listOfReadings;
        this.url = url;
        this.fileLocation = fileLocation;
    }

    public ArrayList<Reading> getListOfReadings() {
        return listOfReadings;
    }

    public void setListOfReadings(ArrayList<Reading> listOfReadings) {
        this.listOfReadings = listOfReadings;
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

    public ArrayList<Reading> getReadingsFromWeb(ArrayList<Reading> listOfReadings) throws IOException {
        Document doc = parseWebPage(url);
        Elements rows = parseElements(doc);
        listOfReadings = parseRows(rows);
        return listOfReadings;
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