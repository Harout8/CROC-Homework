package transport.publictransport;

import enums.DriveUnit;
import enums.TransportKind;
import enums.TransportType;
import transport.PublicTransport;

import java.time.LocalDate;


public class Trolleybus extends PublicTransport {

    public Trolleybus() {
        this.kind = TransportKind.PUBLIC_TRANSPORT;
        this.type = TransportType.TROLLEYBUS;
    }

    public Trolleybus(int id,
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
        this.type = TransportType.TROLLEYBUS;
        this.driveUnit = driveUnit;
    }
}
