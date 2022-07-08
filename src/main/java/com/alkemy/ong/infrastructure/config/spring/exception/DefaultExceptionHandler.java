package com.alkemy.ong.infrastructure.config.spring.exception;

import com.alkemy.ong.application.exception.InvalidCredentialsException;
import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.exception.ThirdPartyException;
import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

  private static final String INVALID_INPUT_DATA = "Invalid input data.";
  private static final String NO_RECORD_WITH_THE_GIVEN_EMAIL = "No record with the given email.";
  private static final String SOMETHING_WENT_WRONG = "Something went wrong.";
  private static final String INVALID_CREDENTIALS = "Please, provide valid credentials.";
  private static final String RECORD_NOT_FOUND = "Record not found in database.";

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    ErrorResponse errorResponse = buildError(HttpStatus.BAD_REQUEST,
        SOMETHING_WENT_WRONG,
        e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e) {
    ErrorResponse errorResponse = buildError(HttpStatus.BAD_REQUEST,
        INVALID_INPUT_DATA,
        collectErrors(e));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = UsernameNotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleUsernameNotFound(
      UsernameNotFoundException e) {
    ErrorResponse errorResponse = buildError(HttpStatus.NOT_FOUND,
        e.getMessage(),
        NO_RECORD_WITH_THE_GIVEN_EMAIL);
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = InvalidCredentialsException.class)
  protected ResponseEntity<ErrorResponse> handleInvalidCredentials(
      InvalidCredentialsException e) {
    ErrorResponse errorResponse = buildError(HttpStatus.UNAUTHORIZED,
        e.getMessage(),
        INVALID_CREDENTIALS);
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = RecordNotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleRecordNotFound(RecordNotFoundException e) {
    ErrorResponse errorResponse = buildError(HttpStatus.NOT_FOUND,
        RECORD_NOT_FOUND,
        e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = UserAlreadyExistsException.class)
  protected ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException e) {
    ErrorResponse errorResponse = buildError(HttpStatus.BAD_REQUEST,
        INVALID_INPUT_DATA,
        e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = ThirdPartyException.class)
  public ResponseEntity<ErrorResponse> handleUploadImageException(ThirdPartyException e) {
    ErrorResponse errorResponse = buildError(
        HttpStatus.INTERNAL_SERVER_ERROR,
        SOMETHING_WENT_WRONG,
        e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

  }

  @ExceptionHandler(value = OperationNotPermittedException.class)
  protected ResponseEntity<ErrorResponse> handleOperationNotPermittedException(
          OperationNotPermittedException e) {
    ErrorResponse errorResponse = buildError(
            HttpStatus.FORBIDDEN,
            "OPERATION NOT PERMITTED",
            e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
  }

  private List<String> collectErrors(MethodArgumentNotValidException e) {
    return e.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
  }

  private ErrorResponse buildError(HttpStatus httpStatus, String message, List<String> moreInfo) {
    return ErrorResponse.builder()
        .statusCode(httpStatus.value())
        .message(message)
        .moreInfo(moreInfo)
        .build();
  }

  private ErrorResponse buildError(HttpStatus httpStatus, String message, String moreInfo) {
    return buildError(httpStatus, message, List.of(moreInfo));
  }

}
