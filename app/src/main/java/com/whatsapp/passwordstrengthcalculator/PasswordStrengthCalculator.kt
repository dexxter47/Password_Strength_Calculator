package com.whatsapp.passwordstrengthcalculator

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import java.util.regex.Matcher
import java.util.regex.Pattern

class PasswordStrengthCalculator: TextWatcher {

    val strengthLevel : MutableLiveData<String> = MutableLiveData()
    val strengthColor : MutableLiveData<Int> = MutableLiveData()

    var lowerCase: MutableLiveData<Int> = MutableLiveData()
    var upperCase: MutableLiveData<Int> = MutableLiveData()
    var digit: MutableLiveData<Int> = MutableLiveData()
    var specialChar: MutableLiveData<Int> = MutableLiveData()
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {
    if(char!=null){
        lowerCase.value = if(char.hasLowerCase()){1} else{0}
        upperCase.value = if(char.hasUpperCase()){1} else{0}
        digit.value = if(char.hasDigit()){1} else{0}
        specialChar.value = if(char.hasSpecialChar()){1} else{0}
        calculateStrength(char)
    }
    }

    private fun calculateStrength(password: CharSequence) {
    if(password.length in 0..7){
        strengthColor.value = R.color.weak
        strengthLevel.value = "Weak"
    }
        else if(password.length in 8..10) {
        if (lowerCase.value == 1 || upperCase.value == 1 || digit.value == 1 || specialChar.value == 1) {
            strengthColor.value = R.color.medium
            strengthLevel.value = "Medium" }
        } else if (password.length in 11..16) {
            if (lowerCase.value == 1 || upperCase.value == 1 || digit.value == 1 || specialChar.value == 1) {
                if (lowerCase.value == 1 && upperCase.value == 1) {
                    strengthColor.value = R.color.strong
                    strengthLevel.value = "Strong"
                }
            }
        } else if (password.length > 16) {
            if (lowerCase.value == 1 || upperCase.value == 1 || digit.value == 1 || specialChar.value == 1) {
                strengthColor.value = R.color.bulletproof
                strengthLevel.value = "Bulletproof"
            }
        }
    }

    private fun CharSequence.hasLowerCase(): Boolean{
        val pattern: Pattern = Pattern.compile("[a-z]")
        val hasLowerCase:Matcher = pattern.matcher(this);
        return hasLowerCase.find()
    }
    private fun CharSequence.hasUpperCase(): Boolean{
        val pattern: Pattern = Pattern.compile("[A-Z]")
        val hasLowerCase:Matcher = pattern.matcher(this);
        return hasLowerCase.find()
    }
    private fun CharSequence.hasDigit(): Boolean{
        val pattern: Pattern = Pattern.compile("[0-9]")
        val hasLowerCase:Matcher = pattern.matcher(this);
        return hasLowerCase.find()
    }
    private fun CharSequence.hasSpecialChar(): Boolean{
        val pattern: Pattern = Pattern.compile("[!@#$%^&*()_=+{}/.<>|\\[\\]~-]")
        val hasLowerCase:Matcher = pattern.matcher(this);
        return hasLowerCase.find()
    }
}