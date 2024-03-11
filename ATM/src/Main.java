import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Connection connection;
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Type 1 - Create Account");
        System.out.println("Type 2 - Execute an operation");
        System.out.print("Please choose a number from 1 - 2: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        chooseOption(choice);
    }

    public static void chooseOption(int choice) {
        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                executeOperation();
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
                int newChoice = scanner.nextInt();
                scanner.nextLine();
                chooseOption(newChoice);
                break;
        }
    }
    public static void createAccount() {
        System.out.print("Enter your customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter your customer number: ");
        String customerNumber = scanner.nextLine();
        System.out.print("Enter your pin number: ");
        String pinNumber = scanner.nextLine();
        insertCustomer(customerName, customerNumber, pinNumber, "0");
    }

    public static void executeOperation() {
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Money Transfer");
        System.out.println("Type 3 - Exit");
        System.out.print("Please choose a number from 1 - 3: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                performCheckingAccountOperations();
                break;
            case 2:
                moneyTransfer();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please choose again.");
                executeOperation();
                break;
        }
    }
    public static void moneyTransfer() {
        System.out.print("Enter Your customer number: ");
        String customerNumber = scanner.nextLine();
        System.out.print("Enter Your pin number: ");
        String pinNumber = scanner.nextLine();
        checkCustomer(customerNumber,pinNumber);
    }
    public static void checkCustomer(String customerNumber, String pinNumber){
        try {
            if (isValidCustomer(customerNumber,pinNumber)){
                checkCustomerTransferring(customerNumber);
            }
            else {
                System.out.println("Invalid Customer. Please try again.");
                moneyTransfer();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executeOperation();
    }
    public static void checkCustomerTransferring( String customerNumber) throws SQLException {
        System.out.print("Please enter the ID you are transferring : ");
        String customerNumberTransferring = scanner.nextLine();
        if (isValidId(customerNumberTransferring)){
            System.out.print("Your");     viewBalance(customerNumber);
            System.out.print("Transferred to him");      viewBalance(customerNumberTransferring);
            checkBalanced(customerNumber,customerNumberTransferring);

        }else {
            System.out.println("Invalid Customer Id . Please try again.");
            checkCustomerTransferring(customerNumber);
        }
    }
    public static void checkBalanced(String customerNumber,String customerNumberTransferring) throws SQLException {
        System.out.print("Enter the transferred amount : ");
        double amountTransferring = scanner.nextDouble();
        scanner.nextLine();
        double customerNumberBalance=getBalance(connection,customerNumber);

        if (customerNumberBalance>amountTransferring){
            double newCustomerNumberBalance=getBalance(connection,customerNumber)-amountTransferring;
            double customerNumberTransferringBalance=getBalance(connection,customerNumber)+amountTransferring;
            updateBalance(connection,customerNumber,newCustomerNumberBalance);
            updateBalance(connection,customerNumberTransferring,customerNumberTransferringBalance);
            System.out.println("The amount has been transferred successfully");
            System.out.println("Want to transferring again ? 1-Yes , 2-No ");
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    checkBalanced(customerNumber,customerNumberTransferring);
                    break;
                case 2:executeOperation();
                break;
            }
        }else {
            System.out.println("Your balance is not enough");
            checkBalanced(customerNumber,customerNumberTransferring);
        }
    }
    public static void performCheckingAccountOperations() {
        System.out.print("Enter Your customer number: ");
        String customerNumber = scanner.nextLine();
        System.out.print("Enter Your pin number: ");
        String pinNumber = scanner.nextLine();
        if (isValidCustomer(customerNumber, pinNumber)) {
            while (true) {
                System.out.println("Checking Account:");
                System.out.println("Type 1 - View Balance");
                System.out.println("Type 2 - Withdraw Funds");
                System.out.println("Type 3 - Deposit Funds");
                System.out.println("Type 4 - Exit");
                System.out.print("Please choose a number from 1 - 4: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        viewBalance(customerNumber);
                        break;
                    case 2:
                        System.out.print("Enter the amount you want to withdraw: ");
                        double amountDraw = scanner.nextDouble();
                        scanner.nextLine();
                        withdrawFunds(customerNumber, amountDraw);
                        break;
                    case 3:
                        System.out.print("Enter the amount you want to deposit: ");
                        String amount = scanner.nextLine();
                        depositFunds(customerNumber, amount);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid Customer. Please try again.");
            performCheckingAccountOperations();
        }
    }

    public static boolean isValidCustomer(String customerNumber, String pinNumber) {
        try {
            String selectSQL = "SELECT * FROM customer WHERE customerNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, customerNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String dbPinNumber = resultSet.getString("pinNumber");
                return pinNumber.equals(dbPinNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isValidId(String customerNumber) {
        try  {
            String selectSQL = "SELECT customerNumber FROM customer";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerNumberTransferring = resultSet.getString("customerNumber");
                if (customerNumberTransferring.equals(customerNumber)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void viewBalance(String customerNumber) {
        try
             {
            String selectSQL = "SELECT * FROM customer WHERE customerNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, customerNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String customerMoney = resultSet.getString("customerMoney");
                System.out.println("Checking Account Balance: " + customerMoney + "$");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void withdrawFunds(String customerNumber, double amount) {
        try {
            double currentBalance = getBalance(connection, customerNumber);
            if (amount > currentBalance) {
                System.out.println("Insufficient balance. Withdrawal failed.");
                return;
            }
            double newBalance = currentBalance - amount;
            updateBalance(connection, customerNumber, newBalance);
            System.out.println("Withdrawal Successful. New Balance: " + newBalance + "$");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static double getBalance(Connection connection, String customerNumber) throws SQLException {

        String selectSQL = "SELECT customerMoney FROM customer WHERE customerNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, customerNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("customerMoney");
                }
            }
        }
        return 0.0;
    }
    private static void updateBalance(Connection connection, String customerNumber, double newBalance) throws SQLException {
        String updateSQL = "UPDATE customer SET customerMoney = ? WHERE customerNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setString(2, customerNumber);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Failed to update balance. Customer not found.");
            }
        }
    }

    public static void depositFunds(String customerNumber, String amount) {
        try  {
            String updateSQL = "UPDATE customer SET customerMoney = customerMoney + ? WHERE customerNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                preparedStatement.setString(1, amount);
                preparedStatement.setString(2, customerNumber);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Amount deposited successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertCustomer(String name, String customerNumber, String pinNumber, String customerMoney) {
        try  {
            if (isCustomerNumberExists(customerNumber)) {
                System.out.println("Customer Number already exists. Please try again with a different number.");
                return;
            }
            String insertSQL = "INSERT INTO customer (customerName, customerNumber, pinNumber, customerMoney) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, customerNumber);
                preparedStatement.setString(3, pinNumber);
                preparedStatement.setString(4, customerMoney);
                preparedStatement.executeUpdate();
                System.out.println("Customer created successfully.");
                executeOperation();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean isCustomerNumberExists( String customerNumber) {
        String query = "SELECT COUNT(*) FROM customer WHERE customerNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customerNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
