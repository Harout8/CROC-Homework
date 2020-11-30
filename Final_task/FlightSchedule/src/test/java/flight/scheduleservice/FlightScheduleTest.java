package flight.scheduleservice;

import database.DataSourceProvider;
import flight.model.Flight;
import flight.repository.FlightRepository;
import flight.scheduleservice.FlightSchedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightScheduleTest {

    @BeforeEach
    public void init() {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
        }

        FlightRepository flightRepository = new FlightRepository(dataSourceProvider.getDataSource());

        // Очистка БД (для удобства проверки)
        for (Flight flight : flightRepository.findAll()) {
            flightRepository.delete(flight);
            System.out.println("Read after delete: " + flightRepository.read(flight)); // проверим, что запись удалена из таблицы
        }
    }


    @Test
    public void testScheduleXmlToDB() {
        List<Flight> expected = new ArrayList<>();
        List<Flight> actual = new ArrayList<>();

        FlightSchedule flightSchedule = new FlightSchedule();
        try {
            flightSchedule.scheduleXmlToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        actual.addAll(flightSchedule.getFlightRepository().findAll());


        Flight flight1 = new Flight(1,
                "in",
                "Yerevan",
                "Krasnodar",
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 1),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0)
        );

        Flight flight2 = new Flight(2,
                "in",
                "Novosibirsk",
                "Krasnodar",
                LocalDate.of(2021, 2, 2),
                LocalDate.of(2021, 2, 2),
                LocalTime.of(11, 0),
                LocalTime.of(18, 0)
        );

        Flight flight3 = new Flight(3,
                "out",
                "Krasnodar",
                "Novosibirsk",
                LocalDate.of(2021, 3, 3),
                LocalDate.of(2021, 3, 3),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0)
        );

        Flight flight4 = new Flight(4,
                "out",
                "Krasnodar",
                "Yerevan",
                LocalDate.of(2021, 4, 4),
                LocalDate.of(2021, 4, 4),
                LocalTime.of(11, 0),
                LocalTime.of(18, 0)
        );

        expected.add(flight1);
        expected.add(flight2);
        expected.add(flight3);
        expected.add(flight4);

        Assertions.assertEquals(expected, actual);

        //System.out.println(expected);
        //System.out.println(actual);
    }
}
