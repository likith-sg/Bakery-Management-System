package employee;

interface EmployeeInterface {
    String getName();
    int getTotalSales();
    double getTotalRevenue();
    void addSales(int sales, double revenue);
    double calculateBonus();
}
