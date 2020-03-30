package transport.cars;

import enums.DriveUnit;
import enums.TransportKind;
import enums.TransportType;
import transport.Transport;

import java.time.LocalDate;


public class PassengerCar extends Transport {

    public PassengerCar() {
        this.kind = TransportKind.CAR;
        this.type = TransportType.PASSENGER_CAR;
    }

    public PassengerCar(int id,
                        LocalDate registrationDate,
                        String manufacturer,
                        String model,
                        int yearOfManufacture,
                        int capacity,
                        DriveUnit driveUnit) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.capacity = capacity;
        this.kind = TransportKind.CAR;
        this.type = TransportType.PASSENGER_CAR;
        this.driveUnit = driveUnit;
    }
}
