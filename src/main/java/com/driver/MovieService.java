package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Movie movie) {
        movieRepository.addMovie(movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director) {
        movieRepository.addDirector(director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        Movie movie = movieRepository.getMovieByName(movieName);
        Director director = movieRepository.getDirectorByName(directorName);

        if (movie != null && director != null) {
            movieRepository.addMovieDirectorPair(movie, director);
            return "Movie-Director pair added successfully";
        } else {
            return "Movie or Director not found";
        }
    }

    public Movie getMovieByName(String name) {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String name) {
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieRepository.getMoviesByDirectorName(director);
    }



    public List<Movie> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String name) {
        Director director = movieRepository.getDirectorByName(name);

        if (director != null) {
            movieRepository.deleteDirectorByName(name);
            return "Director and associated movies deleted successfully";
        } else {
            return "Director not found";
        }
    }

    public String deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
        return "All directors and associated movies deleted successfully";
    }
}

