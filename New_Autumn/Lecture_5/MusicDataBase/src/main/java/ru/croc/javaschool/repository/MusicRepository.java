package ru.croc.javaschool.repository;


import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.model.Music;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Репозиторий для доступа к таблице с данными о песнях (Music).
 */
public class MusicRepository {

    private static final String TABLE_NAME = "music";

    private EmbeddedDataSource dataSource;

    public MusicRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации БД.
     *
     * Оказывается, SQL диалект, используемый JavaDB, не умеет в "IF NOT EXISTS" :(
     * Поэтому пришлось найти небольшой workaround, который предварительно проверяет существование таблицы в базе.
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
                    // поиск мы осуществляем в верхнем. Такие вот приколы
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER PRIMARY KEY, "
                                + "artist VARCHAR(255),"
                                + "title VARCHAR(255),"
                                + "favourite BOOLEAN,"
                                + "release_date DATE"
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
     * Метод поиска всех песен в БД.
     *
     * @return список всех записанных песен
     */
    public List<Music> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Music> musicList = new ArrayList<>();

            while (resultSet.next()) {
                musicList.add(new Music(resultSet.getInt("id"),
                        resultSet.getString("artist"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("favourite"),
                        resultSet.getDate("release_date").toLocalDate()));
            }

            return musicList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    /**
     * Метод создания записи в БД о новой песне.
     *
     * @param music песня
     */
    public void createNew(Music music) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, music.getId());
            statement.setString(2, music.getArtist());
            statement.setString(3, music.getTitle());
            statement.setBoolean(4, music.isFavourite());
            statement.setDate(5, Date.valueOf(music.getReleaseDate()));
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Метод удаления записи в БД о песне.
     *
     * @param music песня
     */
    public void delete(Music music) {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, music.getId());
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Метод чтения записи в БД о песне.
     *
     * @param music песня
     */
    public Music read(Music music) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, music.getId());
            ResultSet resultSet = statement.executeQuery();

            //ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            Music musicFromDB = null;
            while (resultSet.next()) {
                musicFromDB = new Music(resultSet.getInt("id"),
                        resultSet.getString("artist"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("favourite"),
                        resultSet.getDate("release_date").toLocalDate());
            }

            return musicFromDB;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

        return null;
    }

    /**
     * Метод обновления записи в БД о песне.
     *
     * @param music песня
     */
    public void update(Music music, String title) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET title = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, title);
            statement.setInt(2, music.getId());
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }
}
