package garages;

import transport.publictransport.Bus;
import transport.publictransport.Metro;
import transport.publictransport.Tram;
import transport.publictransport.Trolleybus;

import java.util.ArrayList;


public class Depot {
    private String title;
    private ArrayList<Bus> buses;
    private ArrayList<Trolleybus> trolleybuses;
    private ArrayList<Tram> trams;
    private ArrayList<Metro> metros;

    public Depot() {
        this.title = "Public transport depot";
        this.buses = new ArrayList<Bus>();
        this.trolleybuses = new ArrayList<Trolleybus>();
        this.trams = new ArrayList<Tram>();
        this.metros = new ArrayList<Metro>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public ArrayList<Trolleybus> getTrolleybuses() {
        return trolleybuses;
    }

    public void setTrolleybuses(ArrayList<Trolleybus> trolleybuses) {
        this.trolleybuses = trolleybuses;
    }

    public ArrayList<Tram> getTrams() {
        return trams;
    }

    public void setTrams(ArrayList<Tram> trams) {
        this.trams = trams;
    }

    public ArrayList<Metro> getMetros() {
        return metros;
    }

    public void setMetros(ArrayList<Metro> metros) {
        this.metros = metros;
    }
}
