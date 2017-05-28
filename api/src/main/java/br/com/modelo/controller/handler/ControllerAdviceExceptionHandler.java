package br.com.modelo.controller.handler;

import br.com.modelo.util.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    private static final int INTERNAL_ERROR = 500;


	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
	  e.printStackTrace();
        return ResponseEntity.status(INTERNAL_ERROR).body(new ErrorMessage(INTERNAL_ERROR, e.getMessage()));
    }
	
	
}
