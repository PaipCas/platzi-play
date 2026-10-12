package com.platzi.play.domain.dto;

import java.time.LocalDate;

import com.platzi.play.domain.Genre;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MovieDto(
		@Null(message = "El id se genera de manera automática") Long id,
		@NotBlank(message = "El título es obligatorio") @Size(max = 150, message = "Máximo 150 caracteres") String title,
		@NotNull(message = "La duración es obligatoria") @Positive(message = "La duración debe ser mayor a 0") @Max(value = 999, message = "La duración debe ser menor a 1000") Integer duration,
		@NotNull(message = "El género es obligatorio") Genre genre,
		@PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual") LocalDate releaseDate,
		@Positive(message = "El rating no puede ser menor que 0") @DecimalMax(value = "5.0", message = "El rating no puede ser mayor que 5") @Digits(integer = 1, fraction = 1) Double rating,
		Boolean state) {
}