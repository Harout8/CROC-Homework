package garages;

import transport.indmobility.Bicycle;
import transport.indmobility.Scooter;

import java.util.ArrayList;

public class BicycleGarage {
    private String title;
    private ArrayList<Scooter> scooters;
    private ArrayList<Bicycle> bicycles;


    public BicycleGarage() {
        this.title = "Bicycle garage";
        this.scooters = new ArrayList<Scooter>();
        this.bicycles = new ArrayList<Bicycle>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Scooter> getScooters() {
        return scooters;
    }

    public void setScooters(ArrayList<Scooter> scooters) {
        this.scooters = scooters;
    }

    public ArrayList<Bicycle> getBicycles() {
        return bicycles;
    }

    public void setBicycles(ArrayList<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }
}
