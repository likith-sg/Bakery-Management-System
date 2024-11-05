package utils;

public class Customer {
    private String name;
    private boolean repeatedCustomer;

    public Customer(String name, boolean repeatedCustomer) {
        this.name = name;
        this.repeatedCustomer = repeatedCustomer;
    }

    public String getName() {
        return name;
    }

    public boolean isRepeatedCustomer() {
        return repeatedCustomer;
    }
}
