package garages;

import transport.cars.PassengerCar;
import transport.cars.Truck;

import java.util.ArrayList;


public class CarPark {
    private String title;
    private ArrayList<PassengerCar> passengerCars;
    private ArrayList<Truck> trucks;


    public CarPark() {
        this.title = "Car park";
        this.passengerCars = new ArrayList<PassengerCar>();
        this.trucks = new ArrayList<Truck>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<PassengerCar> getPassengerCars() {
        return passengerCars;
    }

    public void setPassengerCars(ArrayList<PassengerCar> passengerCars) {
        this.passengerCars = passengerCars;
    }

    public ArrayList<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(ArrayList<Truck> trucks) {
        this.trucks = trucks;
    }
}
