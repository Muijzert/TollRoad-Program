package tollroad.program;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class TollRoadMain {
    public static void main(String[] args) throws Exception
    {
        try (PrintStream clear = new PrintStream(new FileOutputStream("output.txt"))) {
            clear.println("");
        }
        TollRoad tRoad = initialiseTollRoadFromFile();
        simulateFromFile(tRoad);
        System.out.println(tRoad.getMoneyMade());
        PrintStream output = new PrintStream(new FileOutputStream("output.txt", true));
        output.println("Money made : " + tRoad.getMoneyMade());
        output.close();
    }
    public static TollRoad initialiseTollRoadFromFile() throws IOException, CustomerNotFoundException {
        TollRoad tollRoad = new TollRoad();
        String txtFile;
        List<String> list = new ArrayList<>();
        txtFile = new String(Files.readAllBytes(Paths.get("customerData.txt")));
        for (String holder: txtFile.split("#")) {
            for (String holder1: holder.split(",")) {
                list.add(holder1);
            }
        }
            for(int i = 0; list.size() > i; i=i+8){
                String vehicleType = list.get(i);
                String regNum = list.get(i+1);
                String FirstName = list.get(i+2);
                String LastName = list.get(i+3);
                String vMake = list.get(i+4);
                String vehicleInfo = list.get(i+5);
                String startingBalance = list.get(i+6);
                String DiscountType = list.get(i+7);
                if(vehicleType.compareToIgnoreCase("Van") == 0){
                    Van van = new Van(regNum, vMake, Integer.parseInt(vehicleInfo));
                    //add customer to tollroad
                    tollRoad.addCustomer(new CustomerAccount(FirstName,LastName, van, Integer.parseInt(startingBalance)));
                }else if(vehicleType.compareToIgnoreCase("Car") == 0){
                    //does the same but for car
                    Car car = new Car(regNum, vMake, Integer.parseInt(vehicleInfo));
                    tollRoad.addCustomer(new CustomerAccount(FirstName,LastName, car, Integer.parseInt(startingBalance)));
                }else{
                    //does the same but for truck
                    Truck truck = new Truck(regNum, vMake, Integer.parseInt(vehicleInfo));
                    tollRoad.addCustomer(new CustomerAccount(FirstName,LastName, truck, Integer.parseInt(startingBalance)));
                }
                if (DiscountType.compareToIgnoreCase("STAFF") == 0){
                    tollRoad.findCustomer(regNum).activateStaffDiscount();
                }else if(DiscountType.compareToIgnoreCase("FRIENDS_AND_FAMILY") == 0){
                    tollRoad.findCustomer(regNum).activateFriendsAndFamilyDiscount();
                }
            }
        return tollRoad;
    }
    public static void simulateFromFile(TollRoad road) throws InsufficientAccountBalanceException, IOException, CustomerNotFoundException {
        String txtFile;
        List<String> transactionList = new ArrayList<>();
        txtFile = new String(Files.readAllBytes(Paths.get("transactions.txt")));
        for (String holder: txtFile.split("\\$")) {
            for (String holder1: holder.split(",")) {
                transactionList.add(holder1);
            }
        }
        int c = 0;
        String accountID;
        int fund;
        while(transactionList.size() > c){
            accountID = transactionList.get(c+1);
            //add funds
            if(transactionList.get(c).compareToIgnoreCase("addFunds") == 0){
                fund = Integer.parseInt(transactionList.get(c+2));
                road.findCustomer(accountID).addFunds(fund);
                c = c + 3;
                //makeTrip
            }else{
                road.chargeCustomer(accountID);
                c = c + 2;
            }
        }
    }
}
