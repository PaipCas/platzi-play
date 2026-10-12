package com.platzi.play.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.SuggestRequestDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.service.MovieService;
import com.platzi.play.domain.service.PlatziPlayAiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operations about movies of PlatziPlay")
public class MovieController {
	public final MovieService movieService;
	private final PlatziPlayAiService aiService;

	public MovieController(MovieService movieService, PlatziPlayAiService aiService) {
		this.movieService = movieService;
		this.aiService = aiService;
	}

	@GetMapping()
	public ResponseEntity<List<MovieDto>> getAll() {
		return ResponseEntity.ok(movieService.getAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a movie by id", description = "Return movie than matches the submitted id.", responses = {
			@ApiResponse(responseCode = "200", description = "Found movie"),
			@ApiResponse(responseCode = "404", description = "Not found movie", content = @Content)
	})
	public ResponseEntity<MovieDto> getById(
			@Parameter(description = "Id of the movie to retrieve", example = "9") @PathVariable("id") long id) {
		MovieDto movieDto = movieService.getById(id);

		if (movieDto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(movieDto);
	}

	@PostMapping("/suggest")
	public ResponseEntity<String> generateMoviesSuggestions(@RequestBody SuggestRequestDto suggestRequestDto) {
		return ResponseEntity.ok(this.aiService.generateMoviesSuggestions(suggestRequestDto.userPreferences()));
	}

	@PostMapping
	public ResponseEntity<MovieDto> add(@RequestBody @Valid MovieDto movieDto) {
		MovieDto movieDtoResponse = this.movieService.add(movieDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(movieDtoResponse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovieDto> update(@PathVariable("id") long id,
			@RequestBody @Valid UpdateMovieDto updateMovieDto) {
		return ResponseEntity.ok(this.movieService.update(id, updateMovieDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id) {
		this.movieService.delete(id);
		return ResponseEntity.ok().build();
	}
}