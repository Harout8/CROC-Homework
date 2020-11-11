package ru.croc.javaschool;

import ru.croc.javaschool.db.DataSourceProvider;
import ru.croc.javaschool.model.Music;
import ru.croc.javaschool.repository.MusicRepository;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        }

        MusicRepository musicRepository = new MusicRepository(dataSourceProvider.getDataSource());

        // создадим новую песню
        Music music1 = new Music(1,
                "Mia Martina",
                "Missing you",
                true,
                LocalDate.of(2011, 9, 6));
        musicRepository.createNew(music1);
        // проверим, что запись добавлена в таблицу
        for (Music music : musicRepository.findAll()) {
            System.out.println("FindAll: " + music);
        }

        // проверим, что запись читается из таблицы
        Music music2 = musicRepository.read(music1);
        System.out.println("Read: " + music2);

        // проверим, что запись обновляется в таблице
        musicRepository.update(music1, "Tu Me Manques");
        System.out.println("Update: " + musicRepository.read(music1));

        // проверим, что запись удалена из таблицы
        musicRepository.delete(music1);
        for (Music music : musicRepository.findAll()) {
            System.out.println("Delete: " + music);
        }
    }
}
