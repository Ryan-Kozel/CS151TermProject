package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String account;
    private String transactionType;
    private LocalDate transactionDate;
    private String description;
    private double paymentAmount;
    private double depositAmount;
    
    private static ArrayList<Transaction> transactionList = new ArrayList<>();

    public Transaction(String account, String transactionType, LocalDate transactionDate,
                       String description, double paymentAmount, double depositAmount) {
        this.account = account;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.description = description;
        this.paymentAmount = paymentAmount;
        this.depositAmount = depositAmount;
    }
    
    public String getAccount() { return account; }
    public String getTransactionType() { return transactionType; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public String getDescription() { return description; }
    public double getPaymentAmount() { return paymentAmount; }
    public double getDepositAmount() { return depositAmount; }

    public static void storeTransaction(Transaction newTransaction) throws IOException {
        transactionList.add(newTransaction);

        File transactionFile = new File("Transactions.csv");
        try (PrintWriter out = new PrintWriter(new FileWriter(transactionFile, true))) {
            out.println(newTransaction.getAccount() + "," +
                        newTransaction.getTransactionType() + "," +
                        newTransaction.getTransactionDate() + "," +
                        newTransaction.getDescription() + "," +
                        newTransaction.getPaymentAmount() + "," +
                        newTransaction.getDepositAmount());
        }
    }
    
    public static List<Transaction> getAllTransactions() throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader("Transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    try {
                        String account = data[0].trim();    
                        String transactionType = data[1].trim(); 
                        LocalDate transactionDate = LocalDate.parse(data[2].trim(), formatter); 
                        String description = data[3].trim();
                        double paymentAmount = Double.parseDouble(data[4].trim()); 
                        double depositAmount = Double.parseDouble(data[5].trim()); 

                        transactions.add(new Transaction(account, transactionType, transactionDate,
                                description, paymentAmount, depositAmount));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping line due to number format error: " + line);
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.out.println("Skipping line due to error parsing date: " + line);
                        e.printStackTrace();
                    }
                }
            }
        }
        return transactions;
    }

}
