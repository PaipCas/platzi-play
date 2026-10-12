package com.platzi.play.domain.repository;

import java.util.List;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;

public interface MovieRepository {
	List<MovieDto> getAll();

	MovieDto getById(long id);

	MovieDto save(MovieDto movieDto);

	MovieDto update(long id, UpdateMovieDto updateMovieDto);

	void delete(long id);
}