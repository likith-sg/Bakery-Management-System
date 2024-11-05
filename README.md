# Bakery Management System (BMS)

The Bakery Management System (BMS) is a Java-based backend application designed to optimize bakery operations. It provides an efficient way to manage bakery inventory, employee records, and order processing, helping bakery managers streamline daily tasks, improve order accuracy, and enhance customer satisfaction.

## Problem Statement

### Purpose
The BMS simplifies bakery management by digitalizing core processes. It automates inventory tracking, order calculation, and employee record-keeping, reducing manual effort and minimizing errors.

### Key Users
- **Bakery Manager**: Oversees inventory, processes orders, and manages employee data.
- **Employee**: Assists with customer interactions and supports order handling under managerial guidance.

### Problems Solved
- **Inventory Management**: Tracks items and stock levels to ensure availability and reduce waste.
- **Order Processing**: Automates pricing and total calculations for accuracy and speed.
- **Employee Management**: Maintains employee information, supporting task organization and delegation.
- **Operational Efficiency**: Reduces manual workload, improves order processing accuracy, and ensures smooth service.

## Project Structure

### /bakery
- **BakeryItem.java**: Base class for bakery items.
- **BakeryPantry.java**: Manages inventory of bakery items.
- **BMS2.java**: Main class that initializes and runs the BMS application.
- **Bread.java** and **Cake.java**: Specialized classes for specific bakery items.

### /employee
- **Employee.java**: Represents bakery employees.
- **EmployeeInterface.java**: Defines operations related to employees.

### /utils
- **Customer.java**: Represents a customer entity.
- **OrderCalculator.java**: Provides order calculation functionality.

### ClassDiagram.png
Visualizes class relationships within the BMS project.

## Features

- **Inventory Management**: Real-time tracking of bakery items, availability, and prices.
- **Order Processing**: Accurate order calculations to enhance efficiency.
- **Employee Management**: Records employee data, supporting structured management.
- **User Interface (Backend)**: A Java-based backend that supports easy integration with frontend interfaces.

## Installation

Clone the repository and navigate to the BMS directory:

```bash
git clone https://github.com/likith-sg/Bakery-Management-System.git
cd Bakery-Management-System
```
