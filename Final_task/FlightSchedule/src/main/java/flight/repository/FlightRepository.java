package flight.repository;


import flight.model.Flight;
import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Репозиторий для доступа к таблице с данными о рейсах
 */
public class FlightRepository {

    /**
     * Название таблицы в БД
     */
    private static final String TABLE_NAME = "flight";

    private EmbeddedDataSource dataSource;

    public FlightRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации БД.
     *
     * SQL диалект, используемый JavaDB, не умеет в "IF NOT EXISTS"
     * Поэтому вручную предварительно проверяем существование таблицы в базе.
     */
    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    // Несмотря на то, что мы создаем таблицу в нижнем регистре (и дальше к ней так же обращаемся),
                    // поиск мы осуществляем в верхнем
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});

            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "idNumber INTEGER PRIMARY KEY, "
                                + "in_out VARCHAR(255), "
                                + "cityFrom VARCHAR(255), "
                                + "cityTo VARCHAR(255), "
                                + "departureDate DATE, "
                                + "arrivalDate DATE, "
                                + "departureTime TIME, "
                                + "arrivalTime TIME"
                                + ")");
                System.out.println("Table was successfully initialized");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    /**
     * Метод поиска всех записей в БД.
     *
     * @return список всех записей
     */
    public List<Flight> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
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
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE idNumber = ?";

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
     * @param flight рейс
     */
    public Flight read(Flight flight) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE idNumber = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, flight.getIdNumber());
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
        String sqlQuery = "UPDATE " + TABLE_NAME +
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
