package com.bezkoder.spring.webflux.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldErrorModel {
	String fieldName;
	String fieldError;
}
