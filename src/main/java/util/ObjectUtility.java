package util;

import domain.Reading;
import java.util.ArrayList;

public class ObjectUtility {

    public static void printAllReadings(ArrayList<Reading> listOfReadings) {
        System.out.println("Date time" + "\t" + "Backed" + "\t" + "Avg Wind Direction" + "\t" + "Veered" + "\t" + "Min Wind Speed" + "\t" + "Avg Wind Speed" + "\t" + "Max Wind Speed");
        System.out.println();
        for (Reading reading : listOfReadings){
            System.out.println(reading.getDateTime() + "\t" + reading.getBacked() + "\t" + reading.getAvgWindDirection() + "\t" + reading.getVeered() + "\t" + reading.getMinWindSpeed() + "\t" + reading.getAvgWindSpeed() + "\t" + reading.getMaxWindSpeed());
            //System.out.println("Date time: " + reading.getDateTime() + " Backed: " + reading.getBacked() + " Avg Wind Direction: " + reading.getAvgWindDirection() + " Veered: " + reading.getVeered() + " Min Wind Speed: " + reading.getMinWindSpeed() + " Avg Wind Speed: " + reading.getAvgWindSpeed() + " Max Wind Speed: " + reading.getMaxWindSpeed());
        }
    }
}
