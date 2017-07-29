package nz.shen.velocity.velocity.Model;

import java.util.ArrayList;

public class DirectionsObject {
    public DirectionsObject() {
    }

    private Bounds bounds;

    public Bounds getBounds() {
        return this.bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    private String copyrights;

    public String getCopyrights() {
        return this.copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    private ArrayList<Leg> legs;

    public ArrayList<Leg> getLegs() {
        return this.legs;
    }

    public void setLegs(ArrayList<Leg> legs) {
        this.legs = legs;
    }

    private OverviewPolyline overview_polyline;

    public OverviewPolyline getOverviewPolyline() {
        return this.overview_polyline;
    }

    public void setOverviewPolyline(OverviewPolyline overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    private String summary;

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private ArrayList<String> warnings;

    public ArrayList<String> getWarnings() {
        return this.warnings;
    }

    public void setWarnings(ArrayList<String> warnings) {
        this.warnings = warnings;
    }

    private ArrayList<Object> waypoint_order;

    public ArrayList<Object> getWaypointOrder() {
        return this.waypoint_order;
    }

    public void setWaypointOrder(ArrayList<Object> waypoint_order) {
        this.waypoint_order = waypoint_order;
    }

    public static class Northeast {
        public Northeast() {
        }

        private double lat;

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        private double lng;

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public static class Southwest {
        public Southwest() {
        }

        private double lat;

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        private double lng;

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public static class Bounds {
        public Bounds() {
        }

        private Northeast northeast;

        public Northeast getNortheast() {
            return this.northeast;
        }

        public void setNortheast(Northeast northeast) {
            this.northeast = northeast;
        }

        private Southwest southwest;

        public Southwest getSouthwest() {
            return this.southwest;
        }

        public void setSouthwest(Southwest southwest) {
            this.southwest = southwest;
        }
    }

    public static class Distance {
        public Distance() {
        }

        private String text;

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        private int value;

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class Duration {
        public Duration() {
        }

        private String text;

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        private int value;

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class EndLocation {
        public EndLocation() {
        }

        ;
        private double lat;

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        private double lng;

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public static class StartLocation {
        public StartLocation() {
        }

        ;
        private double lat;

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        private double lng;

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public static class Distance2 {
        public Distance2() {
        }

        ;
        private String text;

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        private int value;

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class Duration2 {
        public Duration2() {
        }

        ;
        private String text;

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        private int value;

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class EndLocation2 {
        public EndLocation2() {
        }

        ;
        private double lat;

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        private double lng;

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public static class Polyline {
        public Polyline() {
        }

        ;
        private String points;

        public String getPoints() {
            return this.points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }

    public static class StartLocation2 {
        public StartLocation2() {
        }

        ;
        private double lat;

        public double getLat() {
            return this.lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        private double lng;

        public double getLng() {
            return this.lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public static class Step {
        public Step() {
        }

        ;
        private Distance2 distance;

        public Distance2 getDistance() {
            return this.distance;
        }

        public void setDistance(Distance2 distance) {
            this.distance = distance;
        }

        private Duration2 duration;

        public Duration2 getDuration() {
            return this.duration;
        }

        public void setDuration(Duration2 duration) {
            this.duration = duration;
        }

        private EndLocation2 end_location;

        public EndLocation2 getEndLocation() {
            return this.end_location;
        }

        public void setEndLocation(EndLocation2 end_location) {
            this.end_location = end_location;
        }

        private String html_instructions;

        public String getHtmlInstructions() {
            return this.html_instructions;
        }

        public void setHtmlInstructions(String html_instructions) {
            this.html_instructions = html_instructions;
        }

        private Polyline polyline;

        public Polyline getPolyline() {
            return this.polyline;
        }

        public void setPolyline(Polyline polyline) {
            this.polyline = polyline;
        }

        private StartLocation2 start_location;

        public StartLocation2 getStartLocation() {
            return this.start_location;
        }

        public void setStartLocation(StartLocation2 start_location) {
            this.start_location = start_location;
        }

        private String travel_mode;

        public String getTravelMode() {
            return this.travel_mode;
        }

        public void setTravelMode(String travel_mode) {
            this.travel_mode = travel_mode;
        }

        private String maneuver;

        public String getManeuver() {
            return this.maneuver;
        }

        public void setManeuver(String maneuver) {
            this.maneuver = maneuver;
        }
    }

    public static class Leg {
        public Leg() {
        }

        ;
        private Distance distance;

        public Distance getDistance() {
            return this.distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }

        private Duration duration;

        public Duration getDuration() {
            return this.duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        private String end_address;

        public String getEndAddress() {
            return this.end_address;
        }

        public void setEndAddress(String end_address) {
            this.end_address = end_address;
        }

        private EndLocation end_location;

        public EndLocation getEndLocation() {
            return this.end_location;
        }

        public void setEndLocation(EndLocation end_location) {
            this.end_location = end_location;
        }

        private String start_address;

        public String getStartAddress() {
            return this.start_address;
        }

        public void setStartAddress(String start_address) {
            this.start_address = start_address;
        }

        private StartLocation start_location;

        public StartLocation getStartLocation() {
            return this.start_location;
        }

        public void setStartLocation(StartLocation start_location) {
            this.start_location = start_location;
        }

        private ArrayList<Step> steps;

        public ArrayList<Step> getSteps() {
            return this.steps;
        }

        public void setSteps(ArrayList<Step> steps) {
            this.steps = steps;
        }

        private ArrayList<Object> traffic_speed_entry;

        public ArrayList<Object> getTrafficSpeedEntry() {
            return this.traffic_speed_entry;
        }

        public void setTrafficSpeedEntry(ArrayList<Object> traffic_speed_entry) {
            this.traffic_speed_entry = traffic_speed_entry;
        }

        private ArrayList<Object> via_waypoint;

        public ArrayList<Object> getViaWaypoint() {
            return this.via_waypoint;
        }

        public void setViaWaypoint(ArrayList<Object> via_waypoint) {
            this.via_waypoint = via_waypoint;
        }
    }

    public static class OverviewPolyline {
        public OverviewPolyline() {
        }

        ;
        private String points;

        public String getPoints() {
            return this.points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }


}
