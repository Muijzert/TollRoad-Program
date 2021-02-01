package tollroad.program;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
/**
 *
 * @author Max
 */
public class CustomerAccount {

    public CustomerAccount(String fName, String sName, Vehicle inpVehicle, int inpBalance){
        firstName = fName;
        surName = sName;
        vehicle = inpVehicle;
        balance = inpBalance;
        discountType = DiscountType.NONE;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public DiscountType getDiscountType() {
        return discountType;
    }
    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
    private String firstName;
    private String surName;
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    private Vehicle vehicle;
    private int balance;
    //DiscountType that defaults to NONE
    private DiscountType discountType = DiscountType.NONE;
    public void activateStaffDiscount(){
        discountType = DiscountType.STAFF;
    }
    public void activateFriendsAndFamilyDiscount(){
        if (discountType != DiscountType.STAFF);
        discountType = DiscountType.FRIENDS_AND_FAMILY;
    }
    //deactivateDiscount() to remove any active discount on the account
    public void deactivateDiscount(){
        discountType = DiscountType.NONE;
    }
    //addFunds to add more credit to the account balance;
    public void addFunds(int amount){
        PrintStream output = null;
        try {
            output = new PrintStream(new FileOutputStream("output.txt", true));
        } catch (FileNotFoundException e) {
        }
        balance = balance + amount;
        System.out.println(getVehicle().getPlate() + " : " + amount + " added successfully");
        output.println(getVehicle().getPlate() + " : " + amount + " added successfully");
        output.close();
    }
    public int makeTrip() throws InsufficientAccountBalanceException {
        PrintStream output = null;
        try {
            output = new PrintStream(new FileOutputStream("output.txt", true));
        } catch (FileNotFoundException e) {
        }
        int tripCost;
        if(discountType.compareTo(DiscountType.STAFF) == 0){
            tripCost = (int) (vehicle.calculateBasicTripCost()*0.5);
        }else if(discountType.compareTo(DiscountType.FRIENDS_AND_FAMILY) == 0){
            tripCost = (int) (vehicle.calculateBasicTripCost()*0.9);
        }else{
            tripCost = vehicle.calculateBasicTripCost();
        }
        //check if balance has enough if not throw error
        if (tripCost <= balance){
            balance = balance - tripCost;
            System.out.println(getVehicle().getPlate() + " : Trip completed successfully");
            output.println(getVehicle().getPlate() + " : Trip completed successfully");
            output.close();
            return tripCost;
        }else{
            System.out.println(getVehicle().getPlate() + " : makeTrip failed. Insufficient funds");
            output.println(getVehicle().getPlate() + " : makeTrip failed. Insufficient funds");
            output.close();
            throw new InsufficientAccountBalanceException("Insufficent money on account");
        }
    }
    private enum DiscountType{
        NONE, STAFF, FRIENDS_AND_FAMILY
    }
    public int compareTo(CustomerAccount custAcount){
        int compareValue = vehicle.getPlate().compareTo(custAcount.getVehicle().getPlate());
        if(compareValue > 0){
            return 1;
        }else if(compareValue < 0){
            return -1;
        }else{
            return compareValue;
        }
    }
    public static void main(String[] args) throws InsufficientAccountBalanceException {
        CustomerAccount customerTest = new CustomerAccount("Luke","Archer",new Car("LUKE15", "Ford", 4), 800);
        System.out.println("CustomerAccount memory location = " + customerTest);
        System.out.println("Balance of customer before trip = " + customerTest.getBalance());
        customerTest.makeTrip();
        System.out.println("Balance of customer after trip = " + customerTest.getBalance());
        System.out.println("Customer's discountType - " + customerTest.getDiscountType());
        customerTest.activateStaffDiscount();
        System.out.println("Customer's changed discountType to STAFF" + customerTest.getDiscountType());
        customerTest.activateFriendsAndFamilyDiscount();
        System.out.println("Customer's changed discountType to FRIENDS_AND_FAMILY - " + customerTest.getDiscountType());
        customerTest.deactivateDiscount();
        System.out.println("Deactivating discount on customer - " + customerTest.getDiscountType());
        CustomerAccount customerTest2 = new CustomerAccount("Geoff","Payne",new Car("GEOFF15", "Ford", 4), 800);
        System.out.println("Comparing numPlate LUKE15 to GEOFF15 - " + customerTest.compareTo(customerTest2));
        System.out.println("Comparing numPlate LUKE15 to LUKE15 - " + customerTest.compareTo(customerTest));
        CustomerAccount customerTest3 = new CustomerAccount("Geoff","Payne",new Car("LUKE16", "Ford", 4), 800);
        System.out.println("Comparing numPlate LUKE15 to LUKE16 - " + customerTest.compareTo(customerTest3));
    }
}
