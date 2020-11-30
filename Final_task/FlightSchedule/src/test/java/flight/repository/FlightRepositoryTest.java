package flight.repository;

import database.DataSourceProvider;
import flight.model.Flight;
import flight.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class FlightRepositoryTest {
    private FlightRepository flightRepository;

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

    @BeforeEach
    public void init() {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
        }

        flightRepository = new FlightRepository(dataSourceProvider.getDataSource());

        // Очистка БД (для удобства проверки)
        for (Flight flight : flightRepository.findAll()) {
            flightRepository.delete(flight);
            //System.out.println("Read after delete: " + flightRepository.read(flight)); // проверим удаления из таблицы
        }
    }

    @Test
    public void testFindAll() {
        List<Flight> expected = new ArrayList<>();
        List<Flight> actual = new ArrayList<>();

        expected.add(flight1);
        expected.add(flight2);
        expected.add(flight3);
        expected.add(flight4);

        flightRepository.createNew(flight1);
        flightRepository.createNew(flight2);
        flightRepository.createNew(flight3);
        flightRepository.createNew(flight4);

        actual.addAll(flightRepository.findAll());

        Assertions.assertEquals(expected, actual);

        //System.out.println(expected);
        //System.out.println(actual);
    }

    @Test
    public void testCreateNew() {
        flightRepository.createNew(flight1);

        Assertions.assertEquals(flight1, flightRepository.read(flight1));

        //System.out.println(flight1);
        //System.out.println(flightRepository.read(flight1));
    }

    @Test
    public void testDelete() {
        flightRepository.createNew(flight1);
        flightRepository.delete(flight1);

        Assertions.assertNull(flightRepository.read(flight1));
    }

    @Test
    public void testUpdate() {
        flightRepository.createNew(flight1);

        Flight updatedFlight1 = new Flight(1,
                "in",
                "Novosibirsk",
                "Krasnodar",
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 1),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0)
        );

        flightRepository.update(updatedFlight1);

        Assertions.assertEquals(updatedFlight1, flightRepository.read(flight1));

        System.out.println("expected: " + updatedFlight1);
        System.out.println("actual: " + flightRepository.read(flight1));
    }

    @Test
    public void testRead() {
        flightRepository.createNew(flight1);

        Assertions.assertEquals(flight1, flightRepository.read(flight1));

        //System.out.println(flight1);
        //System.out.println(flightRepository.read(flight1));
    }
}
