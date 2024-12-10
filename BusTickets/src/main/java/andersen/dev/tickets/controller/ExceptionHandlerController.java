//package andersen.dev.tickets.controller;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionHandlerController {
//	@ExceptionHandler(NoSuchElementException.class)
//	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND)
//				.body(ex.getMessage() != null ? ex.getMessage() : "Resource not found");
//	}
//}
