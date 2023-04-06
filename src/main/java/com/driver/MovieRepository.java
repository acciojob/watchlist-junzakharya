package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    private final Map<String, Movie> movies;
    private final Map<String, Director> directors;
    private final Map<String, List<String>> directorToMovieMap;

    public MovieRepository() {
        movies = new HashMap<>();
        directors = new HashMap<>();
        directorToMovieMap = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(Movie movieName, Director directorName) {
        if (!movies.containsKey(movieName)) {
            throw new IllegalArgumentException("Movie not found: " + movieName);
        }

        if (!directors.containsKey(directorName)) {
            throw new IllegalArgumentException("Director not found: " + directorName);
        }

        List<String> moviesByDirector = directorToMovieMap.getOrDefault(directorName, new ArrayList<>());
        moviesByDirector.add(String.valueOf(movieName));
        directorToMovieMap.put(String.valueOf(directorName), moviesByDirector);
    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return directorToMovieMap.getOrDefault(directorName, new ArrayList<>());
    }

    public List<Movie> findAllMovies() {
        return new ArrayList<>(movies.values());
    }

    public void deleteDirectorByName(String name) {
        if (!directors.containsKey(name)) {
            throw new IllegalArgumentException("Director not found: " + name);
        }

        List<String> moviesByDirector = directorToMovieMap.getOrDefault(name, new ArrayList<>());
        for (String movieName : moviesByDirector) {
            movies.remove(movieName);
        }
        directors.remove(name);
        directorToMovieMap.remove(name);
    }

    public void deleteAllDirectors() {
        movies.clear();
        directors.clear();
        directorToMovieMap.clear();
    }
}

