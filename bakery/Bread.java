package bakery;

class Bread extends BakeryItem {
    public Bread(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public String getType() {
        return "Bread";
    }
}
