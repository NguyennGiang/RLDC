package com.example.detection.Utils

object ValidateUtils {
    fun validateEmail(email: String): Boolean {
        val emailPattern = Regex("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
        return emailPattern.matches(email)
    }
}