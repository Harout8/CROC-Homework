package transport.indmobility;

import enums.DriveUnit;
import enums.TransportKind;
import enums.TransportType;
import transport.Transport;

import java.time.LocalDate;


public class Bicycle extends Transport {

    public Bicycle() {
        this.kind = TransportKind.IND_MOBILITY;
        this.type = TransportType.BICYCLE;
    }

    public Bicycle(int id,
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
        this.kind = TransportKind.IND_MOBILITY;
        this.type = TransportType.BICYCLE;
        this.driveUnit = driveUnit;
    }
}
