package film.scenarios;

import film.characters.FilmCharacter;

import java.util.Arrays;


/**
 *  Класс, описывающий сценарий фильма
 */
public class Scenario {
    private String text;
    private FilmCharacter[] characters;


    public Scenario(String text) {
        this.text = text;
    }

    public Scenario(String text, FilmCharacter[] characters) {
        this.text = text;
        this.characters = characters;
    }


    // Setters:

    public void setText(String text) {
        this.text = text;
    }

    public void setCharacters(FilmCharacter[] characters) {
        this.characters = characters;
    }


    // Getters:

    public String getText() {
        return text;
    }

    public FilmCharacter[] getCharacters() {
        return characters;
    }


    // Добавить персонаж
    public void addCharacter(FilmCharacter filmCharacter) {
        if (characters == null) {
            characters = new FilmCharacter[0];
        }

        characters = Arrays.copyOf(characters, characters.length + 1);
        characters[characters.length - 1] = filmCharacter;
    }

    // Удалить персонажа
    public void deleteCharacter(FilmCharacter character) {
        FilmCharacter[] newCharacters = new FilmCharacter[characters.length - 1];

        for (int i = 0, j = 0; i < characters.length; i++) {
            if (characters[i] != character) {
                newCharacters[j++] = characters[i];
            }
        }

        characters = newCharacters;
    }
}
