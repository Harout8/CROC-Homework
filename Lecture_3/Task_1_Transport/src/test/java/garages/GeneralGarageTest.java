package garages;

import enums.DriveUnit;
import enums.TransportType;
import org.junit.jupiter.api.*;
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
import java.util.ArrayList;

import static enums.TransportType.*;
import static org.junit.jupiter.api.Assertions.*;


class GeneralGarageTest {

    // Создание транспорта
    final PassengerCar passengerCar_bmw = new PassengerCar(1, LocalDate.of(2020, 1, 1), "BMW", "M3 GTR", 2005, 5, DriveUnit.ICE);
    final PassengerCar passengerCar_mazda = new PassengerCar(11, LocalDate.of(2020, 1, 1), "Mazda", "RX-8", 2005, 5, DriveUnit.ICE);

    final Truck truck_1 = new Truck(2, LocalDate.of(2020, 2, 2),"KAMAZ", "Samosval", 1990, 3, 10.0, DriveUnit.ICE);
    final Truck truck_2 = new Truck(22, LocalDate.of(2020, 2, 2),"PAZ", "123", 1990, 3, 5.0, DriveUnit.ICE);

    final Bus bus_1 = new Bus(3, LocalDate.of(2020, 3, 3),"ЛиАЗ", "5292", 2009, 50, 2, DriveUnit.ICE);
    final Bus bus_2 = new Bus(33, LocalDate.of(2020, 3, 3),"ЛиАЗ", "5272", 2009, 50, 3, DriveUnit.ICE);

    final Trolleybus trolleybus_1 = new Trolleybus(4, LocalDate.of(2020, 4, 4),"Сибэлтранссервис", "СТ-6217М", 2012, 40, 401, DriveUnit.ELECTRIC);
    final Trolleybus trolleybus_2 = new Trolleybus(44, LocalDate.of(2020, 4, 4),"Сибэлтранссервис", "СТ-6218М", 2012, 40, 402, DriveUnit.ELECTRIC);

    final Tram tram_1 = new Tram(5, LocalDate.of(2020, 5, 5),"Витязь", "71-931", 2018, 40, 5, DriveUnit.ELECTRIC);
    final Tram tram_2 = new Tram(55, LocalDate.of(2020, 5, 5),"Витязь", "71-932", 2018, 40, 8, DriveUnit.ELECTRIC);

    final Metro metro_1 = new Metro(6, LocalDate.of(2020, 6, 6),"Москва", "767", 2016, 200, 3, DriveUnit.ELECTRIC);
    final Metro metro_2 = new Metro(66, LocalDate.of(2020, 6, 6),"Москва", "777", 2016, 200, 4, DriveUnit.ELECTRIC);

    final Scooter scooter_1 = new Scooter(7, LocalDate.of(2020, 7, 7),"Atemi", "NFS17-1452", 2015, 1, DriveUnit.MUSCULAR);
    final Scooter scooter_2 = new Scooter(77, LocalDate.of(2020, 7, 7),"Atemi", "NFS17-1453", 2015, 1, DriveUnit.MUSCULAR);

    final Bicycle bicycle_1 = new Bicycle(8, LocalDate.of(2020, 8, 8),"Юпитер", "24", 2003, 2, DriveUnit.MUSCULAR);
    final Bicycle bicycle_2 = new Bicycle(88, LocalDate.of(2020, 8, 8),"Юпитер", "2424", 2003, 2, DriveUnit.MUSCULAR);


    // Создание гаража
    GeneralGarage generalGarage = new GeneralGarage();


    @BeforeEach
    public void setUp() throws Exception {
        // Добавление ТС в гараж
        generalGarage.addTransport(passengerCar_bmw);
        generalGarage.addTransport(passengerCar_mazda);

        generalGarage.addTransport(truck_1);
        generalGarage.addTransport(truck_2);

        generalGarage.addTransport(bus_1);
        generalGarage.addTransport(bus_2);

        generalGarage.addTransport(trolleybus_1);
        generalGarage.addTransport(trolleybus_2);

        generalGarage.addTransport(tram_1);
        generalGarage.addTransport(tram_2);

        generalGarage.addTransport(metro_1);
        generalGarage.addTransport(metro_2);

        generalGarage.addTransport(scooter_1);
        generalGarage.addTransport(scooter_2);

        generalGarage.addTransport(bicycle_1);
        generalGarage.addTransport(bicycle_2);
    }



    @DisplayName("Сценарий регистрации ТС")
    @Test
    public void addTransportTest() {
        // Expected
        ArrayList<PassengerCar> expectedPassengerCars = generalGarage.getCarPark().getPassengerCars();
        ArrayList<Truck> expectedTrucks = generalGarage.getCarPark().getTrucks();
        ArrayList<Bus> expectedBuses = generalGarage.getDepot().getBuses();
        ArrayList<Trolleybus> expectedTrolleybuses = generalGarage.getDepot().getTrolleybuses();
        ArrayList<Tram> expectedTrams = generalGarage.getDepot().getTrams();
        ArrayList<Metro> expectedMetros = generalGarage.getDepot().getMetros();
        ArrayList<Scooter> expectedScooters = generalGarage.getBicycleGarage().getScooters();
        ArrayList<Bicycle> expectedBicycles = generalGarage.getBicycleGarage().getBicycles();


        // Actual
        ArrayList<PassengerCar> actualPassengerCars = new ArrayList<PassengerCar>();
        actualPassengerCars.add(passengerCar_bmw);
        actualPassengerCars.add(passengerCar_mazda);

        ArrayList<Truck> actualTrucks = new ArrayList<Truck>();
        actualTrucks.add(truck_1);
        actualTrucks.add(truck_2);

        ArrayList<Bus> actualBuses = new ArrayList<Bus>();
        actualBuses.add(bus_1);
        actualBuses.add(bus_2);

        ArrayList<Trolleybus> actualTrolleybuses = new ArrayList<Trolleybus>();
        actualTrolleybuses.add(trolleybus_1);
        actualTrolleybuses.add(trolleybus_2);

        ArrayList<Tram> actualTrams = new ArrayList<Tram>();
        actualTrams.add(tram_1);
        actualTrams.add(tram_2);

        ArrayList<Metro> actualMetros = new ArrayList<Metro>();
        actualMetros.add(metro_1);
        actualMetros.add(metro_2);

        ArrayList<Scooter> actualScooters = new ArrayList<Scooter>();
        actualScooters.add(scooter_1);
        actualScooters.add(scooter_2);

        ArrayList<Bicycle> actualBicycles = new ArrayList<Bicycle>();
        actualBicycles.add(bicycle_1);
        actualBicycles.add(bicycle_2);


        // Проверка на соответствие
        Assertions.assertEquals(expectedPassengerCars, actualPassengerCars);
        Assertions.assertEquals(expectedTrucks, actualTrucks);
        Assertions.assertEquals(expectedBuses, actualBuses);
        Assertions.assertEquals(expectedTrolleybuses, actualTrolleybuses);
        Assertions.assertEquals(expectedTrams, actualTrams);
        Assertions.assertEquals(expectedMetros, actualMetros);
        Assertions.assertEquals(expectedScooters, actualScooters);
        Assertions.assertEquals(expectedBicycles, actualBicycles);
    }



    @DisplayName("Получение информации о ТС")
    @Test
    void getInfoAboutTest() {
        // Expected
        String info_passengerCar = "\n";
        info_passengerCar += "Информация о ТС '" + passengerCar_bmw.getManufacturer() + " " + passengerCar_bmw.getModel() + "':" + "\n" +
                "  Идентификатор: " + passengerCar_bmw.getId() + "\n" +
                "  Дата регистрации: " + passengerCar_bmw.getRegistrationDate() + "\n" +
                "  Вид: " + passengerCar_bmw.getKind().toString() + "\n" +
                "  Тип: " + passengerCar_bmw.getType().toString() + "\n" +
                "  Тип привода: " + passengerCar_bmw.getDriveUnit().toString() + "\n" +
                "  Производитель: " + passengerCar_bmw.getManufacturer() + "\n" +
                "  Модель: " + passengerCar_bmw.getModel() + "\n" +
                "  Год выпуска: " + passengerCar_bmw.getYearOfManufacture() + "\n" +
                "  Вместимость: " + passengerCar_bmw.getCapacity() + "\n";

        if ((Transport) passengerCar_bmw instanceof Truck) {
            info_passengerCar += "  Грузоподъёмность: " + ((Truck) ((Transport) passengerCar_bmw)).getCarryingCapacity() + "\n";
        }


        String info_truck = "\n";
        info_truck += "Информация о ТС '" + truck_1.getManufacturer() + " " + truck_1.getModel() + "':" + "\n" +
                "  Идентификатор: " + truck_1.getId() + "\n" +
                "  Дата регистрации: " + truck_1.getRegistrationDate() + "\n" +
                "  Вид: " + truck_1.getKind().toString() + "\n" +
                "  Тип: " + truck_1.getType().toString() + "\n" +
                "  Тип привода: " + truck_1.getDriveUnit().toString() + "\n" +
                "  Производитель: " + truck_1.getManufacturer() + "\n" +
                "  Модель: " + truck_1.getModel() + "\n" +
                "  Год выпуска: " + truck_1.getYearOfManufacture() + "\n" +
                "  Вместимость: " + truck_1.getCapacity() + "\n";

        if ((Transport) truck_1 instanceof Truck) {
            info_truck += "  Грузоподъёмность: " + ((Truck) ((Transport) truck_1)).getCarryingCapacity() + "\n";
        }

        // Проверка на соответствие
        Assertions.assertEquals(generalGarage.getInfoAbout(passengerCar_bmw), info_passengerCar);
        Assertions.assertEquals(generalGarage.getInfoAbout(truck_1), info_truck);
    }



    @DisplayName("Получение инф. обо всех ТС данного типа")
    @Test
    void getInfoAboutTransportOfTypeTest() {
        TransportType transportType = PASSENGER_CAR;
        String info;

        info = "Информация о типе транспорта '" + transportType.toString() + "'" + "\n";

        switch (transportType) {
            case PASSENGER_CAR:
                info += "  Общее количество транспортных средств: " + generalGarage.getCarPark().getPassengerCars().size() + "\n";
                for (PassengerCar passengerCar : generalGarage.getCarPark().getPassengerCars()) {
                    info += generalGarage.getInfoAbout(passengerCar);
                }
                break;
            case TRUCK:
                info += "  Общее количество транспортных средств: " + generalGarage.getCarPark().getTrucks().size() + "\n";
                for (Truck truck : generalGarage.getCarPark().getTrucks()) {
                    info += generalGarage.getInfoAbout(truck);
                }
                break;
            case BUS:
                info += "  Общее количество транспортных средств: " + generalGarage.getDepot().getBuses().size() + "\n";
                for (Bus bus : generalGarage.getDepot().getBuses()) {
                    info += generalGarage.getInfoAbout(bus);
                }
                break;
            case TROLLEYBUS:
                info += "  Общее количество транспортных средств: " + generalGarage.getDepot().getTrolleybuses().size() + "\n";
                for (Trolleybus trolleybus : generalGarage.getDepot().getTrolleybuses()) {
                    info += generalGarage.getInfoAbout(trolleybus);
                }
                break;
            case TRAM:
                info += "  Общее количество транспортных средств: " + generalGarage.getDepot().getTrams().size() + "\n";
                for (Tram tram : generalGarage.getDepot().getTrams()) {
                    info += generalGarage.getInfoAbout(tram);
                }
                break;
            case METRO:
                info += "  Общее количество транспортных средств: " + generalGarage.getDepot().getMetros().size() + "\n";
                for (Metro metro : generalGarage.getDepot().getMetros()) {
                    info += generalGarage.getInfoAbout(metro);
                }
                break;
            case SCOOTER:
                info += "  Общее количество транспортных средств: " + generalGarage.getBicycleGarage().getScooters().size() + "\n";
                for (Scooter scooter : generalGarage.getBicycleGarage().getScooters()) {
                    info += generalGarage.getInfoAbout(scooter);
                }
                break;
            case BICYCLE:
                info += "  Общее количество транспортных средств: " + generalGarage.getBicycleGarage().getBicycles().size() + "\n";
                for (Bicycle bicycle : generalGarage.getBicycleGarage().getBicycles()) {
                    info += generalGarage.getInfoAbout(bicycle);
                }
                break;
        }

        //System.out.println(generalGarage.getInfoAboutTransportOfType(PASSENGER_CAR));

        // Проверка на соответствие
        Assertions.assertEquals(generalGarage.getInfoAboutTransportOfType(PASSENGER_CAR), info);
    }



    @DisplayName("Max количество пассажиров")
    @Test
    void getMaxCapacityTest() {
        //System.out.println(generalGarage.getMaxCapacity());
        Assertions.assertEquals(generalGarage.getMaxCapacity(), 682);
    }



    @DisplayName("Кол-во легковых авто, выпущенный n лет назад")
    @Test
    void getCountOfPassCarsRelYearsAgoTest() {
        //System.out.println(generalGarage.getCountOfPassCarsRelYearsAgo(15));
        Assertions.assertEquals(generalGarage.getCountOfPassCarsRelYearsAgo(15), 2);
    }
}