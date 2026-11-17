public class Customer {

    private final CustomerId customerId;
    private final String name;

    public Customer(CustomerId customerId, String name) {

        if (customerId == null) {
            throw new IllegalArgumentException(
                "Customer ID is required"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Customer name is required"
            );
        }

        this.customerId = customerId;
        this.name = name;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void displayDetails() {

        System.out.println(
            "Customer ID: " + customerId.value()
        );

        System.out.println("Name: " + name);
    }
}