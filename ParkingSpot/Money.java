public class Money {

    private final long amountInPaise;
    private final String currency;

    public Money(long amountInPaise, String currency) {

        if (amountInPaise <= 0) {
            throw new IllegalArgumentException(
                "Amount must be positive"
            );
        }

        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException(
                "Currency is required"
            );
        }

        this.amountInPaise = amountInPaise;
        this.currency = currency;
    }

    public Money times(int hours) {

        if (hours <= 0) {
            throw new IllegalArgumentException(
                "Hours must be positive"
            );
        }

        return new Money(
            amountInPaise * hours,
            currency
        );
    }

    public long getAmountInPaise() {
        return amountInPaise;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {

        return String.format(
            "%.2f %s",
            amountInPaise / 100.0,
            currency
        );
    }
}