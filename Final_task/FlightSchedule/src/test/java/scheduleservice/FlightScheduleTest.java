package scheduleservice;


import database.DataSourceProvider;
import model.Flight;
import org.junit.jupiter.api.AfterEach;
import repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class FlightScheduleTest {

    private Flight flight1 = new Flight(1,
            "in",
            "Yerevan",
            "Krasnodar",
            LocalDate.of(2021, 1, 1),
            LocalDate.of(2021, 1, 1),
            LocalTime.of(11, 0),
            LocalTime.of(12, 0)
    );

    private Flight flight2 = new Flight(2,
            "in",
            "Novosibirsk",
            "Krasnodar",
            LocalDate.of(2021, 2, 2),
            LocalDate.of(2021, 2, 2),
            LocalTime.of(11, 0),
            LocalTime.of(18, 0)
    );

    private Flight flight3 = new Flight(3,
            "out",
            "Krasnodar",
            "Novosibirsk",
            LocalDate.of(2021, 3, 3),
            LocalDate.of(2021, 3, 3),
            LocalTime.of(11, 0),
            LocalTime.of(12, 0)
    );

    private Flight flight4 = new Flight(4,
            "out",
            "Krasnodar",
            "Yerevan",
            LocalDate.of(2021, 4, 4),
            LocalDate.of(2021, 4, 4),
            LocalTime.of(11, 0),
            LocalTime.of(18, 0)
    );

    private FlightRepository flightRepository;

    @BeforeEach
    public void init() {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
        }

        flightRepository = new FlightRepository(dataSourceProvider.getDataSource());
    }

    @AfterEach
    public void cleanUp() {
        // Очистка добавленных тестами данных в БД (для удобства проверки)
        flightRepository.delete(flight1);
        flightRepository.delete(flight2);
        flightRepository.delete(flight3);
        flightRepository.delete(flight4);
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


        expected.add(flight1);
        expected.add(flight2);
        expected.add(flight3);
        expected.add(flight4);

        Assertions.assertEquals(expected, actual);

        //System.out.println(expected);
        //System.out.println(actual);
    }
}
