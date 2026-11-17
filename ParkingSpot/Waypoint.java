import java.util.*;

public class Waypoint {

    private final Map<CustomerId, Customer> customers =
        new HashMap<>();

    private final Map<ParkingSpotId, ParkingSpot> parkingSpots =
        new HashMap<>();

    private final Map<BookingId, Booking> bookings =
        new HashMap<>();

    private final Set<String> registeredVehicles =
        new HashSet<>();

    private int nextBookingId = 1;

    public void registerCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                "Customer is required"
            );
        }

        if (customers.containsKey(customer.getCustomerId())) {
            throw new IllegalArgumentException(
                "Customer already registered"
            );
        }

        customers.put(customer.getCustomerId(), customer);

        System.out.println("Customer registered successfully.");
    }

    public void addParkingSpot(ParkingSpot spot) {

        if (spot == null) {
            throw new IllegalArgumentException(
                "Parking spot is required"
            );
        }

        if (parkingSpots.containsKey(spot.getSpotId())) {
            throw new IllegalArgumentException(
                "Parking spot already exists"
            );
        }

        parkingSpots.put(spot.getSpotId(), spot);

        System.out.println("Parking spot added successfully.");
    }

    public void registerVehicle(String vehicleNumber) {

        if (vehicleNumber == null ||
            vehicleNumber.isBlank()) {

            throw new IllegalArgumentException(
                "Vehicle number is required"
            );
        }

        if (!registeredVehicles.add(vehicleNumber)) {
            throw new IllegalArgumentException(
                "Vehicle already registered"
            );
        }

        System.out.println("Vehicle registered successfully.");
    }

    public List<ParkingSpot> getParkingSpots() {

        return List.copyOf(parkingSpots.values());
    }

    public Booking reserve(
        CustomerId customerId,
        ParkingSpotId spotId,
        String vehicleNumber,
        int hours,
        Money hourlyRate
    ) {

        Customer customer = customers.get(customerId);

        if (customer == null) {
            throw new IllegalArgumentException(
                "Customer not found"
            );
        }

        ParkingSpot spot = parkingSpots.get(spotId);

        if (spot == null) {
            throw new IllegalArgumentException(
                "Parking spot not found"
            );
        }

        if (!registeredVehicles.contains(vehicleNumber)) {
            throw new IllegalArgumentException(
                "Vehicle is not registered"
            );
        }

        if (hours <= 0) {
            throw new IllegalArgumentException(
                "Booking hours must be positive"
            );
        }

        if (!spot.isAvailable()) {
            throw new IllegalStateException(
                "Parking spot is not available"
            );
        }

        Money fare = hourlyRate.times(hours);

        BookingId bookingId =
            new BookingId(nextBookingId++);

        Booking booking = new Booking(
            bookingId,
            customerId,
            spotId,
            vehicleNumber,
            fare
        );

        spot.parkVehicle(vehicleNumber);

        Payment payment = new Payment(
            bookingId,
            fare,
            true
        );

        booking.attachPayment(payment);

        bookings.put(bookingId, booking);

        System.out.println("Booking confirmed successfully.");

        return booking;
    }

    public void cancelBooking(BookingId bookingId) {

        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            throw new IllegalArgumentException(
                "Booking not found"
            );
        }

        booking.cancel();

        ParkingSpot spot =
            parkingSpots.get(booking.getSpotId());

        spot.releaseSpot();

        System.out.println("Booking cancelled successfully.");
    }

    public void displayBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Booking booking : bookings.values()) {
            booking.displayBooking();
        }
    }

    public void displayParkingSpots() {

        for (ParkingSpot spot : parkingSpots.values()) {
            spot.displayDetails();
            System.out.println("--------------------------");
        }
    }
}