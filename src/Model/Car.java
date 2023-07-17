

package Model;

import Model.Brand;

public class Car {

    private String carID;
    public Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public void setUpdateCar(Brand brand, String color, String frameID, String engineID){
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }
    
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        return ""+carID + ", " + brand.getBrandID() + ", " + color + ", " + frameID + ", " + engineID ;
    }
    
    public String screenString(){
        return "" + this.brand+"\n"+ this.carID+", "+this.color+", "+ this.frameID+", "+ this.engineID;
    }
    
    public int comparedTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if(d!=0) return 0;
        return this.carID.compareTo(c.carID);
    }
    
}
