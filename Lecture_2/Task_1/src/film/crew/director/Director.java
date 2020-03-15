package film.crew.director;

import film.Movie;

import java.util.Arrays;


/**
 *  Класс, описывающий режиссёра
 */
public class Director {
    private String name;
    private Movie[] movies;


    public Director(String name) {
        this.name = name;
    }

    public Director(String name, Movie[] movies) {
        this.name = name;
        this.movies = movies;
    }


    // Getters:

    public String getName() {
        return name;
    }

    public Movie[] getMovies() {
        return movies;
    }


    // Добавить фильм
    public void addMovie(Movie movie) {
        if (movies == null) {
            movies = new Movie[0];
        }

        movies = Arrays.copyOf(movies, movies.length + 1);
        movies[movies.length - 1] = movie;
    }

    // Удалить фильм
    public void deleteMovie(Movie movie) {
        Movie[] newMovies = new Movie[movies.length - 1];

        for (int i = 0, j = 0; i < movies.length; i++) {
            if (movies[i] != movie) {
                newMovies[j++] = movies[i];
            }
        }

        movies = newMovies;
    }
}
