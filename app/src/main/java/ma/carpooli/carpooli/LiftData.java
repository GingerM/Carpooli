package ma.carpooli.carpooli;

/**
 * Created by Cassandra on 12/28/2017.
 */

public class LiftData {

    String pickupLocation;
    String dropOffLocation;
    Integer seats;
    String date;

    public LiftData(String pickupLocation, String dropOffLocation, Integer seats, String date) {
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.seats = seats;
        this.date = date;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
