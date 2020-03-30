package transport.publictransport;

import enums.DriveUnit;
import enums.TransportKind;
import enums.TransportType;
import transport.PublicTransport;

import java.time.LocalDate;


public class Bus extends PublicTransport {

    public Bus() {
        this.kind = TransportKind.PUBLIC_TRANSPORT;
        this.type = TransportType.BUS;
    }

    public Bus(int id,
               LocalDate registrationDate,
               String manufacturer,
               String model,
               int yearOfManufacture,
               int capacity,
               int route,
               DriveUnit driveUnit) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.capacity = capacity;
        this.route = route;
        this.kind = TransportKind.PUBLIC_TRANSPORT;
        this.type = TransportType.BUS;
        this.driveUnit = driveUnit;
    }
}
