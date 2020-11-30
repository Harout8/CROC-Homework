package jaxb;

import flight.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class JaxbConverterTest {
    @Test
    public void testFromXml() throws IOException {
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

        ArrayList<Flight> actual;
        ArrayList<Flight> expected = new ArrayList<>();

        expected.add(flight1);
        expected.add(flight2);

        Path path = Paths.get("src/main/resources", "Flights_in.xml");
        String xml = Files.readString(path);

        JaxbConverter jacksonConverter = new JaxbConverter();
        actual = jacksonConverter.fromXml(xml, Flight.class);

        System.out.println(actual);

        Assertions.assertEquals(expected, actual);
    }
}
