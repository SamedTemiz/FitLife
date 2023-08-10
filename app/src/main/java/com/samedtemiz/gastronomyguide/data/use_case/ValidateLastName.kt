package com.samedtemiz.gastronomyguide.data.use_case

class ValidateLastName {

    fun execute(lastName: String): ValidationResult {

        if (lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The last name can't be blank"
            )
        }

        if(lastName.length < 3){
            return ValidationResult(
                successful = false,
                errorMessage = "Last name must be longer than 2 characters"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

}