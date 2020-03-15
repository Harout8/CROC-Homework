package film.crew.actor;

import film.characters.FilmCharacter;

import java.util.Arrays;


/**
 *  Класс, описывающий актёра
 */
public class Actor {
    private String name;
    private FilmCharacter[] filmCharacters;


    public Actor() {
        this("No name");
    }

    public Actor(String name) {
        this.name = name;
    }

    public Actor(String name, FilmCharacter[] filmCharacters) {
        this.name = name;
        this.filmCharacters = filmCharacters;
    }


    // Getters:

    public String getName() {
        return name;
    }

    public FilmCharacter[] getFilmCharacters() {
        return filmCharacters;
    }


    // Добавить персонажа
    public void addCharacter(FilmCharacter filmCharacter) {
        if (filmCharacters == null) {
            filmCharacters = new FilmCharacter[0];
        }

        filmCharacters = Arrays.copyOf(filmCharacters, filmCharacters.length + 1);
        filmCharacters[filmCharacters.length - 1] = filmCharacter;

        // Связать персонаж с актёром
        filmCharacter.setActor(this);
    }

    // Удалить персонажа
    public void deleteCharacter(FilmCharacter filmCharacter) {
        FilmCharacter[] newFilmCharacters = new FilmCharacter[filmCharacters.length - 1];

        for (int i = 0, j = 0; i < filmCharacters.length; i++) {
            if (filmCharacters[i] != filmCharacter) {
                newFilmCharacters[j++] = filmCharacters[i];
            }
        }

        filmCharacters = newFilmCharacters;

        // Отвязать персонаж от актёра
        filmCharacter.setActor(null);
    }
}
