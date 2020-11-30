import database.DataSourceProvider;
import flight.model.Flight;
import flight.repository.FlightRepository;
import flight.scheduleservice.FlightSchedule;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        FlightSchedule flightSchedule = new FlightSchedule();

        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        }
        FlightRepository flightRepository = new FlightRepository(dataSourceProvider.getDataSource());

        for (Flight flight : flightRepository.findAll()) {
            flightRepository.delete(flight);
            System.out.println("Read after delete: " + flightRepository.read(flight)); // проверим, что запись удалена из таблицы
        }

        flightSchedule.scheduleXmlToDB();
    }
}
