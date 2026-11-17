public class Main {

    public static void main(String[] args) {

        Waypoint waypoint = new Waypoint();

        System.out.println("WELCOME TO WAYPOINT");
        System.out.println("==========================");

        // 1. Register customer

        Customer customer = new Customer(
            new CustomerId(101),
            "Ramya"
        );

        waypoint.registerCustomer(customer);

        // 2. Add parking spots

        ParkingSpot spot1 = new ParkingSpot(
            new ParkingSpotId(201),
            "Bachupally"
        );

        ParkingSpot spot2 = new ParkingSpot(
            new ParkingSpotId(202),
            "Nizampet"
        );

        waypoint.addParkingSpot(spot1);
        waypoint.addParkingSpot(spot2);

        // 3. Register vehicle

        String vehicleNumber = "TS09AB1234";

        waypoint.registerVehicle(vehicleNumber);

        // 4. Display available parking spots

        System.out.println("\nAVAILABLE PARKING SPOTS");

        waypoint.displayParkingSpots();

        // 5. Calculate hourly rate

        Money hourlyRate = new Money(5000, "INR");

        // 6. Book a parking spot for 2 hours

        System.out.println("\nCREATING BOOKING");

        Booking booking = waypoint.reserve(
            new CustomerId(101),
            new ParkingSpotId(201),
            vehicleNumber,
            2,
            hourlyRate
        );

        // 7. Display booking details

        System.out.println("\nBOOKING DETAILS");

        booking.displayBooking();

        // 8. Try booking the same occupied spot

        System.out.println("\nTESTING DUPLICATE BOOKING");

        try {

            waypoint.reserve(
                new CustomerId(101),
                new ParkingSpotId(201),
                vehicleNumber,
                2,
                hourlyRate
            );

        } catch (IllegalStateException e) {

            System.out.println(e.getMessage());
        }

        // 9. Cancel booking

        System.out.println("\nCANCELLING BOOKING");

        waypoint.cancelBooking(booking.getBookingId());

        // 10. Display parking spots after cancellation

        System.out.println("\nPARKING SPOTS AFTER CANCELLATION");

        waypoint.displayParkingSpots();

        // 11. Display all bookings

        System.out.println("\nALL BOOKINGS");

        waypoint.displayBookings();

        System.out.println("\nPROGRAM COMPLETED");
    }
}