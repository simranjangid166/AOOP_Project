public class Booking {

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }

    private final BookingId bookingId;
    private final CustomerId customerId;
    private final ParkingSpotId spotId;
    private final String vehicleNumber;
    private final Money fare;

    private Status status;

    public Booking(
        BookingId bookingId,
        CustomerId customerId,
        ParkingSpotId spotId,
        String vehicleNumber,
        Money fare
    ) {

        if (bookingId == null ||
            customerId == null ||
            spotId == null ||
            fare == null) {

            throw new IllegalArgumentException(
                "Booking details cannot be null"
            );
        }

        if (vehicleNumber == null ||
            vehicleNumber.isBlank()) {

            throw new IllegalArgumentException(
                "Vehicle number is required"
            );
        }

        this.bookingId = bookingId;
        this.customerId = customerId;
        this.spotId = spotId;
        this.vehicleNumber = vehicleNumber;
        this.fare = fare;

        this.status = Status.PENDING;
    }

    public void attachPayment(Payment payment) {

        if (status != Status.PENDING) {
            throw new IllegalStateException(
                "Booking is not pending"
            );
        }

        if (payment == null ||
            !payment.getBookingId().equals(bookingId)) {

            throw new IllegalArgumentException(
                "Invalid payment"
            );
        }

        if (!payment.isSuccessful()) {
            throw new IllegalStateException(
                "Payment failed"
            );
        }

        status = Status.CONFIRMED;
    }

    public void cancel() {

        if (status == Status.CANCELLED) {
            throw new IllegalStateException(
                "Booking is already cancelled"
            );
        }

        status = Status.CANCELLED;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public ParkingSpotId getSpotId() {
        return spotId;
    }

    public Status getStatus() {
        return status;
    }

    public Money getFare() {
        return fare;
    }

    public void displayBooking() {

        System.out.println(
            "Booking ID: " + bookingId.value()
        );

        System.out.println(
            "Customer ID: " + customerId.value()
        );

        System.out.println(
            "Parking Spot ID: " + spotId.value()
        );

        System.out.println(
            "Vehicle Number: " + vehicleNumber
        );

        System.out.println("Fare: " + fare);

        System.out.println("Status: " + status);

        System.out.println("--------------------------");
    }
}