package bakery;
import java.util.*;
class BakeryPantry {
    private List<BakeryItem> items;

    public BakeryPantry() {
        items = new ArrayList<>();
    }

    public void addItem(BakeryItem item) {
        items.add(item);
    }

    public void removeItem(String name) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    public List<BakeryItem> getItems() {
        return items;
    }

    public BakeryItem getItemByName(String name) {
        for (BakeryItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
