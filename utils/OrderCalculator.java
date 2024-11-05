package utils;
import bakery.BakeryItem;

class OrderCalculator {
    public double calculateOrder(BakeryItem item, int quantity) {
        return item.getPrice() * quantity;
    }

    public double applyDiscount(double amount, double discountPercent) {
        return amount - (amount * discountPercent / 100);
    }
    
    public double applyDiscount(double amount, int flatDiscount) {
        return amount - flatDiscount;
    }
    
}
