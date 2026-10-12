package com.platzi.play.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.platzi.play.persistence.entity.MovieEntity;

public interface CrudMovieEntity extends CrudRepository<MovieEntity, Long> {
	MovieEntity findFirstByTitulo(String titulo);
}