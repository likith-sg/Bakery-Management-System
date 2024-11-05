package employee;

class Employee implements EmployeeInterface {
    private String name;
    private int totalSales;
    private double totalRevenue;

    public Employee(String name) {
        this.name = name;
        this.totalSales = 0;
        this.totalRevenue = 0.0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTotalSales() {
        return totalSales;
    }

    @Override
    public double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public void addSales(int sales, double revenue) {
        this.totalSales += sales;
        this.totalRevenue += revenue;
    }

    @Override
    public double calculateBonus() {
        return totalRevenue * 0.05;
    }
}
