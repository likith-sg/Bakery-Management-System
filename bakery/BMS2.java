package bakery;
import java.util.*;

import employee1.Employee;
import utils.OrderCalculator;
import utils.Customer;
public class BMS2 {
    private static BakeryPantry pantry = new BakeryPantry();
    private static OrderCalculator calculator = new OrderCalculator();
    private static List<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static int itemsAdded = 0;
    private static int itemsUpdated = 0;
    private static int totalSalesRecorded = 0;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addItemToPantry();
                case 2 -> removeItemFromPantry();
                case 3 -> updateStock();
                case 4 -> viewPantry();
                case 5 -> calculateBill();
                case 6 -> checkStock();
                case 7 -> manageEmployees();
                case 8 -> calculateEmployeeBonuses();
                case 9 -> {
                    printSessionSummary();
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Bakery Management System ---");
        System.out.println("1. Add item to pantry");
        System.out.println("2. Remove item from pantry");
        System.out.println("3. Update item stock");
        System.out.println("4. View pantry");
        System.out.println("5. Calculate bill");
        System.out.println("6. Check item stock");
        System.out.println("7. Manage employees and display total sales");
        System.out.println("8. Calculate employee bonuses");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addItemToPantry() {
        System.out.println("\nChoose item type to add:");
        System.out.println("1. Bread");
        System.out.println("2. Cake");
        System.out.print("Enter choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter item stock: ");
        int stock = scanner.nextInt();

        BakeryItem item = switch (typeChoice) {
            case 1 -> new Bread(name, price, stock);
            case 2 -> new Cake(name, price, stock);
            default -> null;
        };

        if (item != null) {
            pantry.addItem(item);
            itemsAdded++;
            System.out.println("Item added successfully!");
        } else {
            System.out.println("Invalid item type. Returning to menu.");
        }
    }

    private static void removeItemFromPantry() {
        System.out.print("Enter the name of the item to remove: ");
        String name = scanner.nextLine();
        pantry.removeItem(name);
        System.out.println("Item removed successfully!");
    }

    private static void updateStock() {
        System.out.print("Enter the name of the item to update: ");
        String name = scanner.nextLine();
        BakeryItem item = pantry.getItemByName(name);

        if (item != null) {
            System.out.print("Enter new stock quantity: ");
            int newStock = scanner.nextInt();
            item.setStock(newStock);
            itemsUpdated++;
            System.out.println("Stock updated successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void viewPantry() {
        System.out.println("\nBakery Pantry:");
        for (BakeryItem item : pantry.getItems()) {
            System.out.println("Name: " + item.getName());
            System.out.println("Type: " + item.getType());
            System.out.println("Price: $" + item.getPrice());
            System.out.println("Stock: " + item.getStock());
            System.out.println();
        }
    }

    private static void calculateBill() {
        System.out.print("Enter employee name: ");
        String employeeName = scanner.nextLine();
        Employee employee = getOrCreateEmployee(employeeName);
    
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Is this a repeated customer? (yes/no): ");
        boolean isRepeatedCustomer = scanner.nextLine().equalsIgnoreCase("yes");
    
        Customer customer = new Customer(customerName, isRepeatedCustomer);
        double totalBill = 0;
    
        System.out.print("Enter number of items to purchase: ");
        int numItems = scanner.nextInt();
        scanner.nextLine();  
    
        for (int i = 0; i < numItems; i++) {
            System.out.print("Enter item name: ");
            String itemName = scanner.nextLine();
            BakeryItem item = pantry.getItemByName(itemName);
    
            if (item != null && item.getStock() > 0) {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();  
    
                if (quantity <= item.getStock()) {
                    double orderAmount = calculator.calculateOrder(item, quantity);
                    totalBill += orderAmount;
                    item.setStock(item.getStock() - quantity);
                    employee.addSales(quantity, orderAmount);
                } else {
                    System.out.println("Insufficient stock for " + itemName);
                }
            } else {
                System.out.println("Item not available or out of stock.");
            }
        }
    
        if (customer.isRepeatedCustomer()) {
            System.out.println("5% discount for repeated customer.");
            totalBill = calculator.applyDiscount(totalBill, 5);
        } else {
            System.out.print("Do you want to apply a discount? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.print("Enter 1 for percentage discount or 2 for flat discount: ");
                int discountType = scanner.nextInt();
                scanner.nextLine();  
    
                if (discountType == 1) {
                    System.out.print("Enter discount percentage: ");
                    double discountPercent = scanner.nextDouble();
                    totalBill = calculator.applyDiscount(totalBill, discountPercent);
                    System.out.println(discountPercent + "% discount applied!");
                } else if (discountType == 2) {
                    System.out.print("Enter flat discount amount: ");
                    int flatDiscount = scanner.nextInt();
                    totalBill = calculator.applyDiscount(totalBill, flatDiscount);
                    System.out.println("Flat discount of $" + flatDiscount + " applied!");
                } else {
                    System.out.println("Invalid choice. No additional discount applied.");
                }
            }
        }
    
        System.out.println("Total bill for " + customer.getName() + ": $" + String.format("%.2f", totalBill));
        totalSalesRecorded++;
    }
    
    

    private static void checkStock() {
        System.out.print("Enter item name to check stock: ");
        String name = scanner.nextLine();
        BakeryItem item = pantry.getItemByName(name);

        if (item != null) {
            System.out.println("Stock for " + item.getName() + ": " + item.getStock());
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void manageEmployees() {
        System.out.println("\nEmployee Management:");
        String header = "Employee Name        Total Sales        Total Revenue";
        String separator = "------------------------------------------------------";

        System.out.println(header);
        System.out.println(separator);

        for (Employee employee : employees) {
            System.out.printf("%-20s %-17d $%-10.2f%n", employee.getName(), employee.getTotalSales(), employee.getTotalRevenue());
        }
    }

    private static void calculateEmployeeBonuses() {
        System.out.println("\nEmployee Bonuses:");
        for (Employee employee : employees) {
            double bonus = employee.calculateBonus();
            System.out.println("Employee: " + employee.getName() + ", Bonus: $" + String.format("%.2f", bonus));
        }
    }

    private static void printSessionSummary() {
        System.out.println("\n--- Session Summary ---");
        System.out.println("Items added: " + itemsAdded);
        System.out.println("Items updated: " + itemsUpdated);
        System.out.println("Total sales recorded: " + totalSalesRecorded);
    }

    private static Employee getOrCreateEmployee(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return employee;
            }
        }
        Employee newEmployee = new Employee(name);
        employees.add(newEmployee);
        return newEmployee;
    }
}
