package by.it_academy.jd2.classifierService.service.validation;

import by.it_academy.jd2.classifierService.DTO.error.ErrorResponseDTO;
import by.it_academy.jd2.classifierService.DTO.error.StructuredErrorResponseDTO;
import by.it_academy.jd2.classifierService.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.server.ResponseStatusException;*/

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    private ErrorResponseDTO buildErrorResponse(String message) {
        return ErrorResponseDTO.builder()
                .logref("error")
                .message(message)
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StructuredErrorResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<StructuredErrorResponseDTO.ErrorDetailDTO> errorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> StructuredErrorResponseDTO.ErrorDetailDTO.builder()
                        .message(error.getDefaultMessage())
                        .field(error.getField())
                        .build())
                .collect(Collectors.toList());

        return StructuredErrorResponseDTO.builder()
                .logref("structured_error")
                .errors(errorDetails)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponseDTO handleValidationException(ValidationException ex) {
        return ErrorResponseDTO.builder()
                .logref("error")
                .message(ex.getMessage())
                .build();
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleAllExceptions(Exception ex) {
        return buildErrorResponse(
                "Сервер не смог корректно обработать запрос. Пожалуйста, обратитесь к администратору.");
    }
    //TODO код ниже закомментирован т.к. пока не используем spring security
    /*

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponseDTO handleUnauthorizedException(ResponseStatusException ex) {
        if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return buildErrorResponse("Для выполнения запроса на данный адрес требуется передать токен авторизации.");
        }
        throw ex;
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponseDTO handleAccessDeniedException(AccessDeniedException ex) {
        return buildErrorResponse("Данному токену авторизации запрещено выполнять запрос на данный адрес.");
    }
*/

}

