package org.bg.winddata;

import org.bg.winddata.domain.Reading;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class WeatherFileWebScraper extends  WebScraper {


    public Elements parseElements(Document doc) {
        Element table = doc.getElementById("datatable");
        Elements rows = table.getElementsByTag("TR");
        //System.out.println(rows);
        return rows;
    }

    public ArrayList<Reading> parseRows(Elements rows) {

        ArrayList<Reading> listOfReadingsTemp = new ArrayList();
        for (Element row : rows) {
            Elements tds = row.getElementsByTag("TD");
            //System.out.println("=============== NEW ROW =============  " + tds.size());

            if (tds.size() == 8) {

                Reading reading = new Reading();

                String date = tds.get(0).text();
                String time = tds.get(1).text();
                String tempDateTime = tds.get(0).text() + ", " + tds.get(1).text();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, H:m", Locale.ENGLISH);
                LocalDateTime dateTime = LocalDateTime.parse(tempDateTime, formatter);
                //System.out.println(dateTime);
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