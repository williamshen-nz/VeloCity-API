package nz.shen.velocity.velocity.Model;

/**
 * Created by allen on 29/07/2017.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manalmohania on 29/7/17.
 */
public class ScenicSpots {

    public static class ScenicSpot{
        String name;
        double lat;
        double longi;

        ScenicSpot(String name, double lat, double longi){
            this.name = name;
            this.lat = lat;
            this.longi = longi;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return lat;
        }

        public double getLongitude() {
            return longi;
        }
    }

    private static ArrayList<ScenicSpot> scenicSpots = populateList();
    private static final String path = "data/";

    private static ArrayList<ScenicSpot> populateList(){

        ArrayList<ScenicSpot> spots = new ArrayList<>();

        String csvFile = path + "Scenic_Spots.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] params = line.split(cvsSplitBy);
                spots.add(new ScenicSpot(params[0], Double.parseDouble(params[1]), Double.parseDouble(params[2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return spots;
    }

    public static List<ScenicSpot> getScenicSpots(){
        return scenicSpots;
    }
}
