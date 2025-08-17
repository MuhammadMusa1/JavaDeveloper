import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class FinanceCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> incomes = new HashMap<>();
        Map<String, Double> expenses = new HashMap<>();
        double totalIncome = 0.0;
        double totalExpenses = 0.0;

        System.out.println("Welcome to Personal Finance Calculator!");

        // Input incomes with categories
        System.out.println("Enter incomes (category and amount; enter 'done' to finish):");
        while (true) {
            System.out.print("Category (or 'done'): ");
            String category = scanner.nextLine().trim();
            if (category.equalsIgnoreCase("done")) break;
            System.out.print("Amount: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount <= 0) throw new NumberFormatException();
                incomes.put(category, amount);
                totalIncome += amount;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Must be positive number.");
            }
        }

        // Input expenses with categories
        System.out.println("Enter expenses (category and amount; enter 'done' to finish):");
        while (true) {
            System.out.print("Category (or 'done'): ");
            String category = scanner.nextLine().trim();
            if (category.equalsIgnoreCase("done")) break;
            System.out.print("Amount: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount <= 0) throw new NumberFormatException();
                expenses.put(category, amount);
                totalExpenses += amount;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Must be positive number.");
            }
        }

        // Calculate balance
        double balance = totalIncome - totalExpenses;

        // Display report
        System.out.println("\n--- Financial Report ---");
        System.out.printf("Total Income: $%.2f%n", totalIncome);
        System.out.println("Incomes:");
        incomes.forEach((cat, amt) -> System.out.printf("  %s: $%.2f%n", cat, amt));
        System.out.printf("Total Expenses: $%.2f%n", totalExpenses);
        System.out.println("Expenses:");
        expenses.forEach((cat, amt) -> System.out.printf("  %s: $%.2f%n", cat, amt));
        System.out.printf("Balance: $%.2f%n", balance);

        // Advice
        if (balance > 0) {
            System.out.println("Great! Positive balance. Consider saving 10% of income.");
        } else if (balance < 0) {
            System.out.printf("Warning: Debt of $%.2f. Reduce expenses by at least 10%%.%n", -balance);
        } else {
            System.out.println("Breaking even. Aim for surplus next time!");
        }

        // Save to file
        try (FileWriter writer = new FileWriter("finance_report.txt")) {
            writer.write("Financial Report\n");
            writer.write(String.format("Total Income: $%.2f\n", totalIncome));
            writer.write("Balance: $%.2f\n", balance);
            System.out.println("Report saved to finance_report.txt");
        } catch (IOException e) {
            System.out.println("Error saving report: " + e.getMessage());
        }

        scanner.close();
    }
}