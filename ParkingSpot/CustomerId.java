
public record CustomerId(int value) {

    public CustomerId {
        if (value <= 0) {
            throw new IllegalArgumentException(
                "Customer ID must be positive"
            );
        }
    }
}
