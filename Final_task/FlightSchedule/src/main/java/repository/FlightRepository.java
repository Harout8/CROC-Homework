package repository;


import static model.Flight.getTableName;

import model.Flight;
import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Репозиторий для доступа к таблице с данными о рейсах
 */
public class FlightRepository {

    private EmbeddedDataSource dataSource;

    public FlightRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        TableOperations.initTable(this.dataSource);
    }


    /**
     * Метод поиска всех записей в БД.
     *
     * @return список всех записей
     */
    public List<Flight> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + getTableName());
            List<Flight> flightList = new ArrayList<>();

            while (resultSet.next()) {
                flightList.add(new Flight(resultSet.getInt("idNumber"),
                        resultSet.getString("in_out"),
                        resultSet.getString("cityFrom"),
                        resultSet.getString("cityTo"),
                        resultSet.getDate("departureDate").toLocalDate(),
                        resultSet.getDate("departureDate").toLocalDate(),
                        resultSet.getTime("departureTime").toLocalTime(),
                        resultSet.getTime("arrivalTime").toLocalTime()));
            }

            return flightList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    /**
     * Метод создания записи в БД.
     *
     * @param flight рейс
     */
    public void createNew(Flight flight) {
        String sqlQuery = "INSERT INTO " + getTableName() + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, flight.getIdNumber());
            statement.setString(2, flight.getInOut());
            statement.setString(3, flight.getCityFrom());
            statement.setString(4, flight.getCityTo());
            statement.setDate(5, Date.valueOf(flight.getDepartureDate()));
            statement.setDate(6, Date.valueOf(flight.getArrivalDate()));
            statement.setTime(7, Time.valueOf(flight.getDepartureTime()));
            statement.setTime(8, Time.valueOf(flight.getArrivalTime()));
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Метод удаления записи в БД.
     *
     * @param flight рейс
     */
    public void delete(Flight flight) {
        String sqlQuery = "DELETE FROM " + getTableName() + " WHERE idNumber = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, flight.getIdNumber());
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Метод чтения записи в БД.
     *
     * @param idNumber рейс
     */
    public Flight findById(int idNumber) {
        String sqlQuery = "SELECT * FROM " + getTableName() + " WHERE idNumber = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, idNumber);
            ResultSet resultSet = statement.executeQuery();

            //ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            Flight recordFromDB = null;
            while (resultSet.next()) {
                recordFromDB = new Flight(resultSet.getInt("idNumber"),
                        resultSet.getString("in_out"),
                        resultSet.getString("cityFrom"),
                        resultSet.getString("cityTo"),
                        resultSet.getDate("departureDate").toLocalDate(),
                        resultSet.getDate("arrivalDate").toLocalDate(),
                        resultSet.getTime("departureTime").toLocalTime(),
                        resultSet.getTime("arrivalTime").toLocalTime());
            }

            return recordFromDB;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

        return null;
    }


    /**
     * Метод обновления записи в БД.
     *
     * @param flight рейс
     */
    public void update(Flight flight) {
        String sqlQuery = "UPDATE " + getTableName() +
                " SET in_out = ?, " +
                "cityFrom = ?, " +
                "cityTo = ?, " +
                "departureDate = ?, " +
                "arrivalDate = ?, " +
                "departureTime = ?, " +
                "arrivalTime = ? " +
                "WHERE idNumber = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, flight.getInOut());
            statement.setString(2, flight.getCityFrom());
            statement.setString(3, flight.getCityTo());
            statement.setDate(4, Date.valueOf(flight.getDepartureDate()));
            statement.setDate(5, Date.valueOf((flight.getArrivalDate())));
            statement.setTime(6, Time.valueOf(flight.getDepartureTime()));
            statement.setTime(7, Time.valueOf(flight.getArrivalTime()));
            statement.setInt(8, flight.getIdNumber());
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }
}
