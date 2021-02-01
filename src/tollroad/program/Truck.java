package tollroad.program;

/**
 *
 * @author Max
 */
public class Truck extends Vehicle{
    public Truck(String inPlate, String inManufacturer, int inpNumTrailers) {
        super(inPlate, inManufacturer);
        numTrailers = inpNumTrailers;
    }

    @Override
    public int calculateBasicTripCost() {
        if (numTrailers == 1){
            return 1250;
        }else{
            return 1500;
        }
    }

    public void setNumTrailers(int numTrailers) {
        this.numTrailers = numTrailers;
    }

    public int getNumTrailers() {
        return numTrailers;
    }

    private int numTrailers;

    public static void main(String[] args)
    {
        Truck testTruck = new Truck("YA34JU","Ford",1);
        System.out.println("Printing truck object" + testTruck);
        System.out.println("NumTrailers = " + testTruck.getNumTrailers()+ "Manufacturer = " + testTruck.getManufacturer() + "numPlate = " +  testTruck.getPlate());
        System.out.println("tripCost = " + testTruck.calculateBasicTripCost());
    }
}
