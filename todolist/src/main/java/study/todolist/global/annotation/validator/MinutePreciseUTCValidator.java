package study.todolist.global.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import study.todolist.global.annotation.MinutePreciseUTC;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class MinutePreciseUTCValidator implements ConstraintValidator<MinutePreciseUTC, ZonedDateTime> {

    @Override
    public boolean isValid(ZonedDateTime value, ConstraintValidatorContext context) {
        if (value == null) return true;

        return value.getOffset().equals(ZoneOffset.UTC) &&
                value.getSecond() == 0 &&
                value.getNano() == 0;
    }
}
