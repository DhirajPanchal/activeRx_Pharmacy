package io.active.pharmacy.base.validation;


import io.active.pharmacy.base.dto.UserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto user = (UserDto) obj;

        System.out.println("_____________ " + user.getPassword() + " - " + user.getMatchingPassword() + " : " + (user.getPassword().equals(user.getMatchingPassword())));

        return user.getPassword().equals(user.getMatchingPassword());
    }

}
