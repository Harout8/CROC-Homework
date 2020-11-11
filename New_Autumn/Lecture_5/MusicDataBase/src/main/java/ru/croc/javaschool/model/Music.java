package ru.croc.javaschool.model;


import java.time.LocalDate;


/**
 * Песня
 */
public class Music {

    /**
     * Идентификатор
     */
    private Integer id;

    /**
     * Исполнитель
     */
    private String artist;

    /**
     * Наименование
     */
    private String title;

    /**
     * Свойство для определения песня избранная или нет
     */
    private boolean isFavourite;

    /**
     * Дата выхода
     */
    private LocalDate releaseDate;


    public Music(Integer id, String artist, String title, boolean isFavourite, LocalDate releaseDate) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.isFavourite = isFavourite;
        this.releaseDate = releaseDate;
    }


    public Integer getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }


    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", isFavourite=" + isFavourite +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
