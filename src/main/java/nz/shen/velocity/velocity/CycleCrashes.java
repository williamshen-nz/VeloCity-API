package nz.shen.velocity.velocity;

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

}
