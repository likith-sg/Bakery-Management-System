package bakery;

class Cake extends BakeryItem {
    public Cake(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public String getType() {
        return "Cake";
    }
}
