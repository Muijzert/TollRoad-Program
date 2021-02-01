/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tollroad.program;

/**
 *
 * @author Max
 */
public class Car extends Vehicle {
    public Car(String inPlate, String inManufacturer, int inpNumberOfSeats) {
        super(inPlate, inManufacturer);
        numberOfSeats = inpNumberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    private int numberOfSeats;

    @Override
    public int calculateBasicTripCost() {
        if (numberOfSeats <= 5){
            return 500;
        }else {
            return 600;
        }
    }
    public static void main(String[] args)
    {
        Car testCar = new Car("T3ST15","Ford",5);
        System.out.println("Printing car object" + testCar);
        System.out.println("Number of seats = " + testCar.getNumberOfSeats()+ "Manufacturer = " +testCar.getManufacturer() + "numPlte = " + testCar.getPlate());
        System.out.println("tripCost = " + testCar.calculateBasicTripCost());
    }
}
