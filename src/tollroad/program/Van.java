package tollroad.program;

/**
 *
 * @author Max
 */
public class Van extends Vehicle {
    public Van(String inPlate, String inManufacturer, int inpPayload) {
        super(inPlate, inManufacturer);
        payload = inpPayload;
    }
    @Override
    public int calculateBasicTripCost() {
        if(payload <= 600){
            return 500;
        }else if(payload <= 800){
            return 750;
        }else
            return 1000;
    }

    public int getPayload() {
        return payload;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }

    private int payload;

    public static void main(String[] args)
    {
        Van testVan = new Van("DYQNJ12","Merc",700);
        System.out.println("Printing Van object " + testVan);
        System.out.println("Payload = " + testVan.getPayload()+ "Manufacturer = " +testVan.getManufacturer() + "numPlate = " + testVan.getPlate());
        System.out.println("tripCost = " + testVan.calculateBasicTripCost());
    }
}
