package repository;


import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;

import static model.Flight.getTableName;


/**
 * Класс для операций с таблицей
 */
public class TableOperations {
    /**
     * Метод инициализации БД.
     *
     * SQL диалект, используемый JavaDB, не умеет в "IF NOT EXISTS"
     * Поэтому вручную предварительно проверяем существование таблицы в базе.
     */
    public static void initTable(EmbeddedDataSource dataSource) {
        System.out.println(String.format("Start initializing %s table", getTableName()));

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    dataSource.getUser().toUpperCase(),
                    // Несмотря на то, что мы создаем таблицу в нижнем регистре (и дальше к ней так же обращаемся),
                    // поиск мы осуществляем в верхнем
                    getTableName().toUpperCase(),
                    new String[]{"TABLE"});

            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + getTableName()
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
}
