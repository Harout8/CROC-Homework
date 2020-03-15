package film;


import film.crew.FilmCrew;
import film.scenarios.Scenario;

import java.time.LocalDate;


/**
 *  Класс, описывающий фильм
 */
public class Movie {
    private String title;       // Название
    private String genre;       // Жанр
    private int year;           // Год выхода
    private String filmStudio;  // Киностудия

    private Scenario scenario;  // Сценарий
    private FilmCrew filmCrew;  // Съёмочная группа


    public Movie () {
        this.title = "No name";
    }

    public Movie (String title) {
        this.title = title;
    }

    public Movie(String title, String genre, int year, String filmStudio, Scenario scenario, FilmCrew filmCrew) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.filmStudio = filmStudio;
        this.scenario = scenario;
        this.filmCrew = filmCrew;
    }


    // Setters:

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        if ((year >= 1895) && (year <= LocalDate.now().getYear())) {
            this.year = year;
        } else if (year < 1895) {
         System.out.println("Кино тогда ещё не существовало !");
        } else if (year > LocalDate.now().getYear()) {
            System.out.println("Будущее ещё не наступило)");
        }
    }

    public void setFilmStudio(String filmStudio) {
        this.filmStudio = filmStudio;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public void setFilmCrew(FilmCrew filmCrew) {
        this.filmCrew = filmCrew;
    }


    // Getters:

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getFilmStudio() {
        return filmStudio;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public FilmCrew getFilmCrew() {
        return filmCrew;
    }
}
