package by.it_academy.jd2.classifierService.service.validation;

import by.it_academy.jd2.classifierService.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateExistence(boolean exists, String title, String entityType) {
        if (exists) {
            throw new ValidationException("title", entityType + " с названием '" + title + "' уже существует.");
        }
    }
}
