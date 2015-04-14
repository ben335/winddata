import domain.Reading;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import util.ObjectUtility;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileLocation = "/Users/benjamingeorge/Dropbox/Bens Documents/Documents/Dev/winddata/src/main/resources/html/winddataSource.htm";
        String url = "http://www.weather-file.com/lymington/graph.htm";


        ArrayList<Reading> listOfReadings = new ArrayList();

        WebScraper webScraper = new WebScraper();
        //listOfReadings = webScraper.getReadingsFromWeb(url, listOfReadings);
        listOfReadings = webScraper.getReadingsFromFile(fileLocation, listOfReadings);



        ObjectUtility.printAllReadings(listOfReadings);
    }



}
