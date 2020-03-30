package transport.cars;

import enums.DriveUnit;
import enums.TransportKind;
import enums.TransportType;
import transport.Transport;

import java.time.LocalDate;


public class Truck extends Transport {
    private double carryingCapacity;    // Грузоподъёмность

    public Truck() {
        this.kind = TransportKind.CAR;
        this.type = TransportType.TRUCK;
    }

    public Truck(int id,
                 LocalDate registrationDate,
                 String manufacturer,
                 String model,
                 int yearOfManufacture,
                 int capacity,
                 double carryingCapacity,
                 DriveUnit driveUnit) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.capacity = capacity;
        this.carryingCapacity = carryingCapacity;
        this.kind = TransportKind.CAR;
        this.type = TransportType.TRUCK;
        this.driveUnit = driveUnit;
    }


    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }
}
