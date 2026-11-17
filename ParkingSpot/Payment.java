public class Payment {

    private final BookingId bookingId;
    private final Money amount;
    private final boolean successful;

    public Payment(
        BookingId bookingId,
        Money amount,
        boolean successful
    ) {

        if (bookingId == null || amount == null) {
            throw new IllegalArgumentException(
                "Booking ID and amount are required"
            );
        }

        this.bookingId = bookingId;
        this.amount = amount;
        this.successful = successful;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public Money getAmount() {
        return amount;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void displayPayment() {

        System.out.println(
            "Booking ID: " + bookingId.value()
        );

        System.out.println("Amount: " + amount);

        System.out.println(
            "Payment Status: " +
            (successful ? "SUCCESSFUL" : "FAILED")
        );
    }
}