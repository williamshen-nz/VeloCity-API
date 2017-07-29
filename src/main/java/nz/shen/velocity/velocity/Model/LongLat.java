package nz.shen.velocity.velocity.Model;

public class LongLat {
    private static final double LONGITUDE_PER_METRE = (1.0 / 3600.0) / 30.89;
    private static final double LATITUDE_PER_METRE = (1.0 / 3600.0) / 25.22;

    // UP, DOWN, RIGHT, LEFT
    // range in metres
    public static Square getRange(double longitude, double latitude, double range) {
        Square edges = new Square(longitude + range * LONGITUDE_PER_METRE, longitude - range * LONGITUDE_PER_METRE,
                latitude + range * LATITUDE_PER_METRE, latitude - range * LATITUDE_PER_METRE);
        return edges;
    }

    public static class Square {
        private final Double up;
        private final Double down;
        private final Double left;
        private final Double right;

        Square(Double up, Double down, Double left, Double right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

        public Double getDown() {
            return down;
        }

        public Double getLeft() {
            return left;
        }

        public Double getRight() {
            return right;
        }

        public Double getUp() {
            return up;
        }

        public boolean isInRange(double longitude, double latitude) {
            return latitude <= right && latitude >= left && longitude <= up && longitude >= down;
        }
    }
}
