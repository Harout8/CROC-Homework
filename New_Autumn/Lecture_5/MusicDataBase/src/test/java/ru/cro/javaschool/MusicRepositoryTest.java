package ru.cro.javaschool;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.db.DataSourceProvider;
import ru.croc.javaschool.model.Music;
import ru.croc.javaschool.repository.MusicRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MusicRepositoryTest {

    MusicRepository musicRepository;

    @BeforeEach
    public void init() {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
        }

        musicRepository = new MusicRepository(dataSourceProvider.getDataSource());
    }

     @Test
     public void testFindAll() {
         Music music1 = new Music(1,
                 "Mia Martina",
                 "Missing you",
                 true,
                 LocalDate.of(2011, 9, 6));

         List<Music> musicListEcpected = new ArrayList<>();
         List<Music> musicListActual = new ArrayList<>();

         musicListEcpected.add(music1);

         musicRepository.createNew(music1);
         for (Music music : musicRepository.findAll()) {
             musicListActual.add(music);
         }

         Assertions.assertEquals(musicListEcpected.toString(), musicListActual.toString());
         System.out.println(musicListEcpected);
         System.out.println(musicListActual);
    }

    @Test
    public void testCreateNew() {
        Music music1 = new Music(1,
                "Mia Martina",
                "Missing you",
                true,
                LocalDate.of(2011, 9, 6));

        musicRepository.createNew(music1);

        Assertions.assertEquals(music1.toString(), musicRepository.read(music1).toString());
        System.out.println(musicRepository.read(music1));
        System.out.println(music1);
    }

    @Test
    public void testRead() {
        Music music1 = new Music(1,
                "Mia Martina",
                "Missing you",
                true,
                LocalDate.of(2011, 9, 6));

        musicRepository.createNew(music1);

        Assertions.assertEquals(music1.toString(), musicRepository.read(music1).toString());
        System.out.println(music1);
        System.out.println(musicRepository.read(music1));
    }

    @Test
    public void testUpdate() {
        Music music1 = new Music(1,
                "Mia Martina",
                "Missing you",
                true,
                LocalDate.of(2011, 9, 6));

        Music music2 = new Music(1,
                "Mia Martina",
                "Tu Me Manques",
                true,
                LocalDate.of(2011, 9, 6));

        musicRepository.createNew(music1);
        musicRepository.update(music1, "Tu Me Manques");

        Assertions.assertEquals(music2.toString(), musicRepository.read(music1).toString());
        System.out.println(music2);
        System.out.println(musicRepository.read(music1));
    }

    @Test
    public void testDelete() {
        Music music1 = new Music(1,
                "Mia Martina",
                "Missing you",
                true,
                LocalDate.of(2011, 9, 6));

        musicRepository.createNew(music1);
        musicRepository.delete(music1);

        Assertions.assertNull(musicRepository.read(music1));
    }
}
