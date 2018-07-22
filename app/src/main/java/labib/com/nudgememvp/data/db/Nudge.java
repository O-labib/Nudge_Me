package labib.com.nudgememvp.data.db;

public class Nudge {

    long id;
    String message;
    String placeName;
    double lat;
    double lon;
    long time;

    public Nudge(long id, String message, String placeName, double lat, double lon, long time) {
        this.id = id;
        this.message = message;
        this.placeName = placeName;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getPlaceName() {
        return placeName;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public long getTime() {
        return time;
    }
}
