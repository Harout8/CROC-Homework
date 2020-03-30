package transport;

import enums.DriveUnit;
import enums.TransportKind;
import enums.TransportType;

import java.time.LocalDate;


public abstract class Transport {
    protected int id;                       // Идентификатор
    protected LocalDate registrationDate;   // Дата регистрации
    protected String manufacturer;          // Производитель
    protected String model;                 // Модель
    protected int yearOfManufacture;        // Год выпуска
    protected int capacity;                 // Вместимость
    protected TransportKind kind;           // Вид
    protected TransportType type;           // Тип
    protected DriveUnit driveUnit;          // Привод (способ приведения ТС в движение)


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TransportKind getKind() {
        return kind;
    }

    public TransportType getType() {
        return type;
    }

    public DriveUnit getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(DriveUnit driveUnit) {
        this.driveUnit = driveUnit;
    }
}
