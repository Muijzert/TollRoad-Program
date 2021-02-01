package tollroad.program;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

/**
 *
 * @author Max
 */
public class TollRoad {
    private HashMap<String, CustomerAccount> accountHM = new HashMap();
    public int getMoneyMade() {
        return moneyMade;
    }
    public void setMoneyMade(int moneyMade) {
        this.moneyMade = moneyMade;
    }
    private int moneyMade;
    public TollRoad(){
        moneyMade = 0;
    }
    //addCustomer adds a new account to the list of customers
    public void addCustomer(CustomerAccount acc){
        accountHM.put(acc.getVehicle().getPlate(),acc);
    }
    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException {
        PrintStream output = null;
        try {
            output = new PrintStream(new FileOutputStream("output.txt", true));
        } catch (FileNotFoundException e) {
        }
        if(accountHM.containsKey(regNum)){
            return accountHM.get(regNum);
        }else{
            System.out.println(regNum + " : Could not find customer");
            output.println(regNum + " : Could not find customer");
            output.close();
            throw new CustomerNotFoundException("No customer found when finding customer");
        }
    }
    public void chargeCustomer(String registrationNumber) throws InsufficientAccountBalanceException, CustomerNotFoundException {
        try{
            findCustomer(registrationNumber);
            int tripCost = accountHM.get(registrationNumber).makeTrip();
            moneyMade = moneyMade + tripCost;
        } catch(CustomerNotFoundException e){
        } catch (InsufficientAccountBalanceException e){
        }
    }
    public static void main(String[] args) throws InsufficientAccountBalanceException, CustomerNotFoundException {
        TollRoad testRoad = new TollRoad();
        testRoad.addCustomer(new CustomerAccount("Luke","Archer",new Car("LUKE15", "Ford", 4), 800));
        System.out.println("FindCustomer.getSurName = " + testRoad.findCustomer("LUKE15").getSurName());
        System.out.println("FindCustomer.getBalance = " + testRoad.findCustomer("LUKE15").getBalance());
        testRoad.chargeCustomer("LUKE15");
        System.out.println("MoneyMade = " + testRoad.moneyMade);
        System.out.println("FindCustomer.getBalance = " + testRoad.findCustomer("LUKE15").getBalance());
    }
}
