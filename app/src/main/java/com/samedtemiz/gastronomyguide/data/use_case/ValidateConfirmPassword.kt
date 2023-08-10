package com.samedtemiz.gastronomyguide.data.use_case

class ValidateConfirmPassword {

    fun execute(password: String, confirmPassword: String): ValidationResult{
        if(confirmPassword.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The confirm password can't be blank"
            )
        }
        if(password != confirmPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

}