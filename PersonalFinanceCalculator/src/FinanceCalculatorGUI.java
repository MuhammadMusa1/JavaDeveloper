import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class FinanceCalculatorGUI extends JFrame {
    private Map<String, Double> incomes = new HashMap<>();
    private Map<String, Double> expenses = new HashMap<>();
    private JTextField categoryField = new JTextField(10);
    private JTextField amountField = new JTextField(10);
    private JTextArea reportArea = new JTextArea(10, 30);
    private double totalIncome = 0.0;
    private double totalExpenses = 0.0;

    public FinanceCalculatorGUI() {
        setTitle("Personal Finance Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);

        // Buttons
        JButton addIncomeBtn = new JButton("Add Income");
        addIncomeBtn.addActionListener(new AddIncomeListener());
        JButton addExpenseBtn = new JButton("Add Expense");
        addExpenseBtn.addActionListener(new AddExpenseListener());
        JButton calculateBtn = new JButton("Calculate");
        calculateBtn.addActionListener(new CalculateListener());

        inputPanel.add(addIncomeBtn);
        inputPanel.add(addExpenseBtn);
        inputPanel.add(calculateBtn);

        // Report area
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        add(inputPanel);
        add(scrollPane);

        setVisible(true);
    }

    private class AddIncomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addEntry(incomes, true);
        }
    }

    private class AddExpenseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addEntry(expenses, false);
        }
    }

    private void addEntry(Map<String, Double> map, boolean isIncome) {
        String category = categoryField.getText().trim();
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0 || category.isEmpty()) throw new NumberFormatException();
            map.put(category, amount);
            if (isIncome) totalIncome += amount;
            else totalExpenses += amount;
            categoryField.setText("");
            amountField.setText("");
            reportArea.append((isIncome ? "Income" : "Expense") + " added: " + category + " - $" + amount + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Category required, amount must be positive.");
        }
    }

    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double balance = totalIncome - totalExpenses;
            StringBuilder report = new StringBuilder();
            report.append("--- Financial Report ---\n");
            report.append(String.format("Total Income: $%.2f\n", totalIncome));
            incomes.forEach((cat, amt) -> report.append(String.format("  %s: $%.2f\n", cat, amt)));
            report.append(String.format("Total Expenses: $%.2f\n", totalExpenses));
            expenses.forEach((cat, amt) -> report.append(String.format("  %s: $%.2f\n", cat, amt)));
            report.append(String.format("Balance: $%.2f\n", balance));

            if (balance > 0) {
                report.append("Great! Positive balance.\n");
            } else if (balance < 0) {
                report.append("Warning: Debt. Reduce expenses.\n");
            } else {
                report.append("Breaking even.\n");
            }

            reportArea.setText(report.toString());

            // Save to file
            try (FileWriter writer = new FileWriter("finance_report.txt")) {
                writer.write(report.toString());
                reportArea.append("Report saved to finance_report.txt\n");
            } catch (IOException ex) {
                reportArea.append("Error saving report: " + ex.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinanceCalculatorGUI::new);
    }
}