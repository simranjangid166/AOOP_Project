public class ParkingSpot {

    private final ParkingSpotId spotId;
    private final String location;

    private String vehicleNumber;

    public ParkingSpot(
        ParkingSpotId spotId,
        String location
    ) {

        if (spotId == null) {
            throw new IllegalArgumentException(
                "Spot ID is required"
            );
        }

        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException(
                "Location is required"
            );
        }

        this.spotId = spotId;
        this.location = location;
        this.vehicleNumber = null;
    }

    public void parkVehicle(String vehicleNumber) {

        if (!isAvailable()) {
            throw new IllegalStateException(
                "Parking spot is already occupied"
            );
        }

        if (vehicleNumber == null ||
            vehicleNumber.isBlank()) {

            throw new IllegalArgumentException(
                "Vehicle number is required"
            );
        }

        this.vehicleNumber = vehicleNumber;
    }

    public void releaseSpot() {

        if (isAvailable()) {
            throw new IllegalStateException(
                "Parking spot is already free"
            );
        }

        vehicleNumber = null;
    }

    public boolean isAvailable() {
        return vehicleNumber == null;
    }

    public ParkingSpotId getSpotId() {
        return spotId;
    }

    public String getLocation() {
        return location;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void displayDetails() {

        System.out.println("Spot ID: " + spotId.value());

        System.out.println("Location: " + location);

        System.out.println(
            "Status: " +
            (isAvailable() ? "FREE" : "OCCUPIED")
        );

        System.out.println(
            "Vehicle: " +
            (vehicleNumber == null ? "None" : vehicleNumber)
        );
    }
}