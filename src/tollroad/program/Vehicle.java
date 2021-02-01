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
public abstract class Vehicle {
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    private String plate;
    private String manufacturer;
    public Vehicle(String inPlate, String inManufacturer){
        plate = inPlate;
        manufacturer = inManufacturer;
    }
    public abstract int calculateBasicTripCost();
}
