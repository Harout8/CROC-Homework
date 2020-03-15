package main;


import film.Movie;
import film.characters.FilmCharacter;
import film.crew.FilmCrew;
import film.crew.actor.Actor;
import film.crew.director.Director;
import film.crew.scenarist.Scenarist;
import film.scenarios.Scenario;


/**
 * @author Khachatryan Haroutyun
 *
 * Task 1:
 * Описать фильм структурой классов. Вложенность классов должна быть не меньше трёх(например, фильм -> сценарий -> персонаж).
 * Число классов не менее 7. Классы должны быть разбиты на пакеты. В методе main нужно создать не менее 3 фильмов и
 * заполнить их описание.
 */
public class MainClass {

    public static void main(String[] args) {

        // Создание актёров:

        Actor actor_BenHur_1 = new Actor("Джек Хьюстон");
        Actor actor_BenHur_2 = new Actor("Тоби Кеббелл");

        Actor actor_taxi_1 = new Actor("Сами Насери");
        Actor actor_taxi_2 = new Actor("Фредерик Дифенталь");
        Actor actor_taxi_3 = new Actor("Марион Котийяр");

        Actor actor_onePlusOne_1 = new Actor("Франсуа Клюзе");
        Actor actor_onePlusOne_2 = new Actor("Омар Си");


        // Создание режиссёров:

        Director director_BenHur = new Director("Тимур Бекмамбетов");
        Director director_taxi = new Director("Жерар Пирес");
        Director director_onePlusOne = new Director("Оливье Накаш");


        // Создание сценаристов:

        Scenarist scenarist_BenHur = new Scenarist("Кит Р. Кларк");
        Scenarist scenarist_taxi = new Scenarist("Люк Бессон");
        Scenarist scenarist_onePlusOne = new Scenarist("Эрик Толедано");


        // Создание съёмочной группы

        FilmCrew filmCrew_BenHur = new FilmCrew(director_BenHur, scenarist_BenHur);
        filmCrew_BenHur.addActor(actor_BenHur_1);
        filmCrew_BenHur.addActor(actor_BenHur_2);

        FilmCrew filmCrew_taxi = new FilmCrew(director_taxi, scenarist_taxi);
        filmCrew_taxi.addActor(actor_taxi_1);
        filmCrew_taxi.addActor(actor_taxi_2);
        filmCrew_taxi.addActor(actor_taxi_3);

        FilmCrew filmCrew_onePlusOne = new FilmCrew(new Actor[] {actor_onePlusOne_1, actor_onePlusOne_2}, director_onePlusOne, scenarist_onePlusOne);


        // Создание сценариев:

        Scenario scenario_BenHur = new Scenario("Ben-Hur scenario ...");
        Scenario scenario_taxi = new Scenario("Taxi scenario ...");
        Scenario scenario_onePlusOne = new Scenario("1+1 scenario ...");


        // Создание персонажей:

        FilmCharacter character_BenHur_1 = new FilmCharacter("Иуда Бен-Гур", actor_BenHur_1);
        FilmCharacter character_BenHur_2 = new FilmCharacter("Мессала", actor_BenHur_2);

        FilmCharacter character_taxi_1 = new FilmCharacter("Даниэль Моралес", actor_taxi_1);
        FilmCharacter character_taxi_2 = new FilmCharacter("Эмильен Кутан-Корбадек", actor_taxi_2);
        FilmCharacter character_taxi_3 = new FilmCharacter("Лили", actor_taxi_3);

        FilmCharacter character_onePlusOne_1 = new FilmCharacter("Филипп Поццо ди Борго", actor_onePlusOne_1);
        FilmCharacter character_onePlusOne_2 = new FilmCharacter("Дрисс Бассари", actor_onePlusOne_2);


        // Добавление персонажей в сценарии:

        scenario_BenHur.addCharacter(character_BenHur_1);
        scenario_BenHur.addCharacter(character_BenHur_2);

        scenario_taxi.addCharacter(character_taxi_1);
        scenario_taxi.addCharacter(character_taxi_2);
        scenario_taxi.addCharacter(character_taxi_3);

        scenario_onePlusOne.addCharacter(character_onePlusOne_1);
        scenario_onePlusOne.addCharacter(character_onePlusOne_2);


        // Назначение персонажей актёрам:

        actor_BenHur_1.addCharacter(character_BenHur_1);
        actor_BenHur_2.addCharacter(character_BenHur_2);

        actor_taxi_1.addCharacter(character_taxi_1);
        actor_taxi_2.addCharacter(character_taxi_2);
        actor_taxi_3.addCharacter(character_taxi_3);

        actor_onePlusOne_1.addCharacter(character_onePlusOne_1);
        actor_onePlusOne_2.addCharacter(character_onePlusOne_2);


        // Создание фильмов:

        Movie movie_BenHur = new Movie("Бен-Гур", "Пеплум", 2016, "Paramount Pictures", scenario_BenHur, filmCrew_BenHur);
        Movie movie_taxi = new Movie("Такси", "Комедия", 1998, "Canal+", scenario_taxi, filmCrew_taxi);
        Movie movie_onePlusOne = new Movie("1+1", "Трагикомедия", 2011, "Gaumont", scenario_onePlusOne, filmCrew_onePlusOne);


        // Результаты:

        System.out.println("\n");

        System.out.println("Фильм Бен-Гур:");
        System.out.println("  Название: " + movie_BenHur.getTitle());
        System.out.println("  Жанр: " + movie_BenHur.getGenre());
        System.out.println("  Год выхода: " + movie_BenHur.getYear());
        System.out.println("  Киностудия: " + movie_BenHur.getFilmStudio());
        System.out.println("  Сценарий: " + movie_BenHur.getScenario().getText());
        System.out.println("  Режиссёр: " + movie_BenHur.getFilmCrew().getDirector().getName());
        System.out.println("  Сценарист: " + movie_BenHur.getFilmCrew().getScenarist().getName());
        System.out.println("  Актёры: " + movie_BenHur.getFilmCrew().getActorsNames());

        System.out.println("\n");

        System.out.println("Фильм Такси:");
        System.out.println("  Название: " + movie_taxi.getTitle());
        System.out.println("  Жанр: " + movie_taxi.getGenre());
        System.out.println("  Год выхода: " + movie_taxi.getYear());
        System.out.println("  Киностудия: " + movie_taxi.getFilmStudio());
        System.out.println("  Сценарий: " + movie_taxi.getScenario().getText());
        System.out.println("  Режиссёр: " + movie_taxi.getFilmCrew().getDirector().getName());
        System.out.println("  Сценарист: " + movie_taxi.getFilmCrew().getScenarist().getName());
        System.out.println("  Актёры: " + movie_taxi.getFilmCrew().getActorsNames());

        System.out.println("\n");

        System.out.println("Фильм 1+1:");
        System.out.println("  Название: " + movie_onePlusOne.getTitle());
        System.out.println("  Жанр: " + movie_onePlusOne.getGenre());
        System.out.println("  Год выхода: " + movie_onePlusOne.getYear());
        System.out.println("  Киностудия: " + movie_onePlusOne.getFilmStudio());
        System.out.println("  Сценарий: " + movie_onePlusOne.getScenario().getText());
        System.out.println("  Режиссёр: " + movie_onePlusOne.getFilmCrew().getDirector().getName());
        System.out.println("  Сценарист: " + movie_onePlusOne.getFilmCrew().getScenarist().getName());
        System.out.println("  Актёры: " + movie_onePlusOne.getFilmCrew().getActorsNames());

        System.out.println("\n");
    }
}
