import java.util.Scanner;

public class FinanceCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalIncome = 0.0;
        double totalExpenses = 0.0;

        System.out.println("Welcome to Personal Finance Calculator!");
        System.out.println("Enter your incomes (enter 0 to finish):");

        // Input incomes
        while (true) {
            System.out.print("Income amount: ");
            double income = scanner.nextDouble();
            if (income == 0) break;
            totalIncome += income;
        }

        System.out.println("Enter your expenses (enter 0 to finish):");

        // Input expenses
        while (true) {
            System.out.print("Expense amount: ");
            double expense = scanner.nextDouble();
            if (expense == 0) break;
            totalExpenses += expense;
        }

        // Calculate and display balance
        double balance = totalIncome - totalExpenses;
        System.out.println("\n--- Financial Report ---");
        System.out.printf("Total Income: $%.2f%n", totalIncome);
        System.out.printf("Total Expenses: $%.2f%n", totalExpenses);
        System.out.printf("Balance: $%.2f%n", balance);

        // Simple advice
        if (balance > 0) {
            System.out.println("Great job! You have a positive balance.");
        } else if (balance < 0) {
            System.out.println("Warning: You're in debt. Consider reducing expenses.");
        } else {
            System.out.println("You're breaking even. Keep an eye on your finances!");
        }

        scanner.close();
    }
}