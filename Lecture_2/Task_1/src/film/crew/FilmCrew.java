package film.crew;

import film.crew.actor.Actor;
import film.crew.director.Director;
import film.crew.scenarist.Scenarist;

import java.util.Arrays;


/**
 *  Класс, описывающий съёмочную группу
 */
public class FilmCrew {
    private Actor[] actors;
    private Director director;
    private Scenarist scenarist;


    public FilmCrew(Director director, Scenarist scenarist) {
        this.director = director;
        this.scenarist = scenarist;
    }

    public FilmCrew(Actor[] actors, Director director, Scenarist scenarist) {
        this.actors = actors;
        this.director = director;
        this.scenarist = scenarist;
    }


    // Getters:

    public Actor[] getActors() {
        return actors;
    }

    public String getActorsNames() {
        String actorsNames = "";

        for (Actor actor : actors) {
            actorsNames += actor.getName() + ". ";
        }

        return actorsNames;
    }

    public Director getDirector() {
        return director;
    }

    public Scenarist getScenarist() {
        return scenarist;
    }


    // Setters:

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setScenarist(Scenarist scenarist) {
        this.scenarist = scenarist;
    }


    // Добавить актёра
    public void addActor(Actor actor) {
        if (actors == null) {
            actors = new Actor[0];
        }

        actors = Arrays.copyOf(actors, actors.length + 1);
        actors[actors.length - 1] = actor;
    }

    // Удалить актёра
    public void deleteActor(Actor actor) {
        Actor[] newActors = new Actor[actors.length - 1];

        for (int i = 0, j = 0; i < actors.length; i++) {
            if (actors[i] != actor) {
                newActors[j++] = actors[i];
            }
        }

        actors = newActors;
    }
}
