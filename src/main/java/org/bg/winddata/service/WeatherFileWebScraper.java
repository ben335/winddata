package org.bg.winddata.service;

import org.bg.winddata.domain.Reading;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherFileWebScraper extends WebScraper {


    public Elements parseElements(Document doc) {
        Element table = doc.getElementById("datatable");
        Elements rows = table.getElementsByTag("TR");
        return rows;
    }

    public ArrayList<Reading> parseRows(Elements rows) {

        ArrayList<Reading> listOfReadingsTemp = new ArrayList();
        for (Element row : rows) {
            Elements tds = row.getElementsByTag("TD");

            if (tds.size() == 8) {
                Reading reading = new Reading();
                String date = tds.get(0).text();
                String time = tds.get(1).text();
                String tempDateTime = tds.get(0).text() + ", " + tds.get(1).text();
                SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy, H:m");
                Date parsedDate = null;
                try {
                    parsedDate = dateFormat.parse(tempDateTime);
                } catch (ParseException e) {
                    logger.error("Exception when parsing rows in the weather file scraper: ", e);
                    e.printStackTrace();
                }
                Timestamp dateTime = new java.sql.Timestamp(parsedDate.getTime());

                reading.setDateTime(dateTime);
                reading.setBacked(Integer.parseInt(tds.get(2).text()));
                reading.setAvgWindDirection(Integer.parseInt(tds.get(3).text()));
                reading.setVeered(Integer.parseInt(tds.get(4).text()));
                reading.setMinWindSpeed(Double.parseDouble(tds.get(5).text()));
                reading.setAvgWindSpeed(Double.parseDouble(tds.get(6).text()));
                reading.setMaxWindSpeed(Double.parseDouble(tds.get(7).text()));
                listOfReadingsTemp.add(reading);
            }
        }

        return listOfReadingsTemp;
    }

}
