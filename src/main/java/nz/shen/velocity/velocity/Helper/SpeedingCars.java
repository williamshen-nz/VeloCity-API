package nz.shen.velocity.velocity.Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by manalmohania on 29/7/17.
 */
public class SpeedingCars {

    private HashMap<String, Integer> dangerLevel = new HashMap<>(); // coord to integer
    private HashMap<String, String> locsToCoords;
    private final String path = "data/";

    public SpeedingCars(){
        locsToCoords = fillHm();
        setDangerLocations();
    }

    public HashMap<String, Integer> getDangerLevel() {
        return dangerLevel;
    }

    public HashMap<String, String> getLocsToCoords() {
        return locsToCoords;
    }

    static class Coordinate{
        double lat;
        double longi;

        Coordinate(double lat, double longi){
            this.lat = lat;
            this.longi = longi;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Coordinate
                    && ((Coordinate) obj).lat == this.lat
                    && ((Coordinate) obj).longi == this.longi;
        }

        @Override
        public int hashCode() {
            return Double.toString(lat).hashCode() + Double.toString(longi).hashCode();
        }

        @Override
        public String toString() {
            return lat + "," + longi;
        }
    }

    private HashMap<String, String> fillHm(){
        HashMap<String, String> hm = new HashMap<>();

        String csvFile = path + "Traffic_Camera_Locations_-_Point_Map.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            boolean first = true;

            while ((line = br.readLine()) != null) {

                if (first) {
                    first = false;
                    continue;
                }

                // use comma as separator
                String[] params = line.split(cvsSplitBy);
                String original = params[1];
                original = original.replaceAll("[^\\d]", "");
                original = original.replaceFirst("^0+(?!$)", "");
                hm.put(original, new Coordinate(Double.parseDouble(params[2]), Double.parseDouble(params[3])).toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hm;
    }

    private void addToMap(String locCode) {
        String coord = getCoordinatesForLocCode(locCode);
        if (dangerLevel.containsKey(coord)) {
            dangerLevel.put(coord, dangerLevel.get(coord) + 1);
        }
        else {
            dangerLevel.put(coord, 1);
        }
    }

    public int getDangerLevel(String coord) {
        if (dangerLevel.containsKey(coord)){
            return dangerLevel.get(coord);
        }
        return 0;
    }

    private String getCoordinatesForLocCode(String locCode) {
        if (locsToCoords.containsKey(locCode)) {
            return locsToCoords.get(locCode);
        }
        return "";
    }



    private void setDangerLocations(){
        String csvFile = path + "Traffic_camera_offences_and_fines.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] params = line.split(cvsSplitBy);
                addToMap(params[4]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // for a given data point,

}