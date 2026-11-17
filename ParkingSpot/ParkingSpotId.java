public record ParkingSpotId(int value) {

    public ParkingSpotId {
        if (value <= 0) {
            throw new IllegalArgumentException(
                "Parking Spot ID must be positive"
            );
        }
    }
}