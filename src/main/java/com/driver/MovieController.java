package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Add a movie
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok("Movie added successfully!");
    }

    // Add a director
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return ResponseEntity.ok("Director added successfully!");
    }

    // Pair an existing movie and director
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName,
                                                       @RequestParam("director") String directorName) {
        movieService.addMovieDirectorPair(movieName, directorName);
        return ResponseEntity.ok("Movie-Director pair added successfully!");
    }

    // Get Movie by movie name
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName) {
        Movie movie = movieService.getMovieByName(movieName);
        return ResponseEntity.ok(movie);
    }

    // Get Director by director name
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName) {
        Director director = movieService.getDirectorByName(directorName);
        return ResponseEntity.ok(director);
    }

    // Get List of movies name for a given director name
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName) {
        List<String> movies = movieService.getMoviesByDirectorName(directorName);
        return ResponseEntity.ok(movies);
    }

    // Get List of all movies added
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies() {
        List<Movie> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }

    // Delete a director and its movies from the records
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName) {
        movieService.deleteDirectorByName(directorName);
        return ResponseEntity.ok("Director and its movies deleted successfully!");
    }

    // Delete all directors and all movies by them from the records
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return ResponseEntity.ok("All directors and their movies deleted successfully!");
    }
}
