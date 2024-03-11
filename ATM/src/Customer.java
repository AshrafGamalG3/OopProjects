public class Customer {

    private String customerNumber;
    private String pinNumber;

    private String customerName;
    private String customerMoney;

    public Customer(String customerName,String customerNumber, String pinNumber ) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.customerName = customerName;
    }

    public Customer() {
    }

    static {
        System.out.println("Welcome to the ATM Project!!");
    }
    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
