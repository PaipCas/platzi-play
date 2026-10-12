package com.platzi.play.domain.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record UpdateMovieDto(
		@NotBlank(message = "El título es obligatorio") String title,
		@PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual") LocalDate releaseDate,
		@Positive(message = "El rating no puede ser menor que 0") @DecimalMax(value = "5.0", message = "El rating no puede ser mayor que 5") @Digits(integer = 1, fraction = 1) Double rating,
		@NotNull(message = "El estado es obligatorio") Boolean state) {
}