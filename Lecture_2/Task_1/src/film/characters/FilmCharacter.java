package film.characters;

import film.crew.actor.Actor;


/**
 *  Класс, описывающий персонажа фильма
 */
public class FilmCharacter {
    private String name;    // Имя персонажа
    private Actor actor;    // Актёр, исполняющий роль персонажа


    public FilmCharacter(String name) {
        this.name = name;
    }

    public FilmCharacter(String name, Actor actor) {
        this.name = name;
        this.actor = actor;
    }


    // Getters & Setters:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
