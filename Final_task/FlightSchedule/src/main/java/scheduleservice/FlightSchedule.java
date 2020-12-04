package scheduleservice;


import database.DataSourceProvider;
import model.Flight;
import repository.FlightRepository;
import jaxbtools.JaxbConverter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Расписание авиарейсов
 */
public class FlightSchedule {
    /**
     * Конвертер JAXB
     */
    private JaxbConverter jacksonConverter;

    /**
     * Путь к XML-файлу с данными о входящих рейсах
     */
    private Path inPath = Paths.get("src/main/resources", "Flights_in.xml");

    /**
     * Путь к XML-файлу с данными об исходящих рейсах
     */
    private Path outPath = Paths.get("src/main/resources", "Flights_out.xml");

    /**
     * Строковое представление данных о входящих рейсах
     */
    private String inXml;

    /**
     * Строковое представление данных об исходящих рейсах
     */
    private String outXml;

    /**
     * Список входящих рейсов
     */
    private ArrayList<Flight> inFlights;

    /**
     * Список исходящих рейсов
     */
    private ArrayList<Flight> outFlights;

    /**
     * Ссылка на репозиторий для доступа к таблице с данными о рейсах
     */
    private FlightRepository flightRepository;


    /**
     * Метод чтения данных о входящих и исходящих рейсах из XML файлов и записи этих данных в таблицу БД
     *
     * @throws IOException
     */
    public void scheduleXmlToDB() throws IOException {
        try {
            //inXml = Files.readString(inPath);   // Since Java 11
            //outXml = Files.readString(outPath); // Since Java 11

            inXml = new String(Files.readAllBytes(inPath), StandardCharsets.UTF_8);   // Java 7
            outXml = new String(Files.readAllBytes(outPath), StandardCharsets.UTF_8); // Java 7

            System.out.println(inXml);
            System.out.println(outXml);

            inFlights =  JaxbConverter.fromXml(inXml, Flight.class);
            outFlights =  JaxbConverter.fromXml(outXml, Flight.class);

            System.out.println("inFlights: " + inFlights);
            System.out.println("outFlights: " + outFlights);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        }

        flightRepository = new FlightRepository(dataSourceProvider.getDataSource());

        //  Запишем в БД
        for (Flight flight : inFlights) {
            flightRepository.createNew(flight);
        }
        for (Flight flight : outFlights) {
            flightRepository.createNew(flight);
        }

        // Проверим, что записи добавлены в таблицу
        for (Flight flight : flightRepository.findAll()) {
            System.out.println("FindAll: " + flight);
        }
    }

    public FlightRepository getFlightRepository() {
        return flightRepository;
    }
}
