package nz.shen.velocity.velocity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CycleCrashes {
    public class CycleCrash {
        private String date;
        private String severity;
        private double latitude;
        private double longitude;

        public CycleCrash(String date, String severity, double latitude, double longitude) {
            this.date = date;
            this.severity = severity;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getDate() {
            return date;
        }

        public String getSeverity() {
            return severity;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    private List<CycleCrash> crashes = new ArrayList<>();

    public List<CycleCrash> getCrashes() {
        return crashes;
    }

    public void addCrash(String date, String severity, double latitude, double longitude) {
        crashes.add(new CycleCrash(date, severity, latitude, longitude));
    }

    private void readCrashesCSV(){
        String csvFile = "data/Cyclist_Crashes.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\t";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            boolean first = true;
            while ((line = br.readLine()) != null) {

                if (first) {
                    first = false;
                    continue;
                }
                // use comma as separator
                String[] crash = line.split(cvsSplitBy);

                addCrash(crash[0], crash[1], Double.parseDouble(crash[6]), Double.parseDouble(crash[7]));

            }

        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new CycleCrashes().readCrashesCSV();
    }
}
