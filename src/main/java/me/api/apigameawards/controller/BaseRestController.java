package me.api.apigameawards.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import me.api.apigameawards.service.exception.BusinessException;
import me.api.apigameawards.service.exception.NoContentException;

@RequestMapping("/api")
public abstract class BaseRestController {

	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<Void> handleNoContent(NoContentException exception) {
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiErrorDTO> handlerBusinessException(BusinessException exception) {
		ApiErrorDTO error = new ApiErrorDTO(exception.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiErrorDTO> handlerUnexpectedException(BusinessException exception) {
		ApiErrorDTO error = new ApiErrorDTO("Ops, ocorreu um erro inesperado.");
		return ResponseEntity.internalServerError().body(error);
	}
}
