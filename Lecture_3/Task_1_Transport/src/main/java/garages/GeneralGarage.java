package garages;

import enums.TransportType;
import transport.Transport;
import transport.cars.PassengerCar;
import transport.cars.Truck;
import transport.indmobility.Bicycle;
import transport.indmobility.Scooter;
import transport.publictransport.Bus;
import transport.publictransport.Metro;
import transport.publictransport.Tram;
import transport.publictransport.Trolleybus;

import java.time.LocalDate;


public class GeneralGarage {
    private String title;
    private CarPark carPark;
    private Depot depot;
    private BicycleGarage bicycleGarage;


    public GeneralGarage() {
        this.title = "General garage";
        this.carPark = new CarPark();
        this.depot = new Depot();
        this.bicycleGarage = new BicycleGarage();
    }


    public void addTransport(PassengerCar passengerCar) {
        carPark.getPassengerCars().add(passengerCar);
    }

    public void addTransport(Truck truck) {
        carPark.getTrucks().add(truck);
    }

    public void addTransport(Bus bus) {
        depot.getBuses().add(bus);
    }

    public void addTransport(Trolleybus trolleybus) {
        depot.getTrolleybuses().add(trolleybus);
    }

    public void addTransport(Tram tram) {
        depot.getTrams().add(tram);
    }

    public void addTransport(Metro metro) {
        depot.getMetros().add(metro);
    }

    public void addTransport(Scooter scooter) {
        bicycleGarage.getScooters().add(scooter);
    }

    public void addTransport(Bicycle bicycle) {
        bicycleGarage.getBicycles().add(bicycle);
    }


    public String getInfoAbout(Transport transport) {
        String info = "\n";

        info += "Информация о ТС '" + transport.getManufacturer() + " " + transport.getModel() + "':" + "\n" +
                "  Идентификатор: " + transport.getId() + "\n" +
                "  Дата регистрации: " + transport.getRegistrationDate() + "\n" +
                "  Вид: " + transport.getKind().toString() + "\n" +
                "  Тип: " + transport.getType().toString() + "\n" +
                "  Тип привода: " + transport.getDriveUnit().toString() + "\n" +
                "  Производитель: " + transport.getManufacturer() + "\n" +
                "  Модель: " + transport.getModel() + "\n" +
                "  Год выпуска: " + transport.getYearOfManufacture() + "\n" +
                "  Вместимость: " + transport.getCapacity() + "\n";

        if (transport instanceof Truck) {
            info += "  Грузоподъёмность: " + ((Truck) transport).getCarryingCapacity() + "\n";
        }

        return info;
    }


    public String getInfoAboutTransportOfType(TransportType transportType) {
        String info;

        info = "Информация о типе транспорта '" + transportType.toString() + "'" + "\n";

        switch (transportType) {
           case PASSENGER_CAR:
               info += "  Общее количество транспортных средств: " + carPark.getPassengerCars().size() + "\n";
               for (PassengerCar passengerCar : carPark.getPassengerCars()) {
                    info += this.getInfoAbout(passengerCar);
               }
               break;
           case TRUCK:
               info += "  Общее количество транспортных средств: " + carPark.getTrucks().size() + "\n";
               for (Truck truck : carPark.getTrucks()) {
                   info += this.getInfoAbout(truck);
               }
               break;
           case BUS:
               info += "  Общее количество транспортных средств: " + depot.getBuses().size() + "\n";
               for (Bus bus : depot.getBuses()) {
                   info += this.getInfoAbout(bus);
               }
               break;
           case TROLLEYBUS:
               info += "  Общее количество транспортных средств: " + depot.getTrolleybuses().size() + "\n";
               for (Trolleybus trolleybus : depot.getTrolleybuses()) {
                   info += this.getInfoAbout(trolleybus);
               }
               break;
           case TRAM:
               info += "  Общее количество транспортных средств: " + depot.getTrams().size() + "\n";
               for (Tram tram : depot.getTrams()) {
                   info += this.getInfoAbout(tram);
               }
               break;
           case METRO:
               info += "  Общее количество транспортных средств: " + depot.getMetros().size() + "\n";
               for (Metro metro : depot.getMetros()) {
                   info += this.getInfoAbout(metro);
               }
               break;
           case SCOOTER:
               info += "  Общее количество транспортных средств: " + bicycleGarage.getScooters().size() + "\n";
               for (Scooter scooter : bicycleGarage.getScooters()) {
                   info += this.getInfoAbout(scooter);
               }
               break;
           case BICYCLE:
               info += "  Общее количество транспортных средств: " + bicycleGarage.getBicycles().size() + "\n";
               for (Bicycle bicycle : bicycleGarage.getBicycles()) {
                   info += this.getInfoAbout(bicycle);
               }
               break;
       }

        return info;
    }


    public int getMaxCapacity() {
        int totalCapacity = 0;

        for (Bus bus : depot.getBuses()) {
            totalCapacity += bus.getCapacity();
        }

        for (Trolleybus trolleybus : depot.getTrolleybuses()) {
            totalCapacity += trolleybus.getCapacity();
        }

        for (Tram tram : depot.getTrams()) {
            totalCapacity += tram.getCapacity();
        }

        for (Metro metro : depot.getMetros()) {
            totalCapacity += metro.getCapacity();
        }

        return totalCapacity;
    }


    public int getCountOfPassCarsRelYearsAgo(int n) {
        int count = 0;

        for (PassengerCar passengerCar : carPark.getPassengerCars()) {
            if (passengerCar.getYearOfManufacture() == (LocalDate.now().getYear() - n)) {
                count++;
            }
        }

        return count;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CarPark getCarPark() {
        return carPark;
    }

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public BicycleGarage getBicycleGarage() {
        return bicycleGarage;
    }

    public void setBicycleGarage(BicycleGarage bicycleGarage) {
        this.bicycleGarage = bicycleGarage;
    }
}
