package com.whatsapp.passwordstrengthcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var color: Int = R.color.weak


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val passwordStrengthCalculator = PasswordStrengthCalculator()
        password_input.addTextChangedListener(passwordStrengthCalculator)

        //Observers
        passwordStrengthCalculator.strengthLevel.observe(this, Observer{strengthLevel ->
            displayStrengthLevel(strengthLevel)
        })
        passwordStrengthCalculator.strengthColor.observe(this, Observer{strengthColor ->
            color = strengthColor
        })
        passwordStrengthCalculator.lowerCase.observe(this, Observer{value ->
            displayPasswordSuggestions(value,lowerCase_img,lowerCase_txt)
        })
        passwordStrengthCalculator.upperCase.observe(this, Observer{value ->
            displayPasswordSuggestions(value,upperCase_img,upperCase_txt)
        })
        passwordStrengthCalculator.digit.observe(this, Observer{value ->
            displayPasswordSuggestions(value,digits_img,digits_txt)
        })
        passwordStrengthCalculator.specialChar.observe(this, Observer{value ->
            displayPasswordSuggestions(value,specialCharacter_img,specialCharacter_txt)
        })
    }

    private fun displayPasswordSuggestions(value: Int, imageView: ImageView, textview: TextView) {
    if(value == 1){
        imageView.setColorFilter(ContextCompat.getColor(this,R.color.bulletproof))
        textview.setTextColor(ContextCompat.getColor(this,R.color.bulletproof))
    }
        else{
        imageView.setColorFilter(ContextCompat.getColor(this,R.color.darkGray))
        textview.setTextColor(ContextCompat.getColor(this,R.color.darkGray))
    }
    }

    private fun displayStrengthLevel(strengthLevel: String) {
    button.isEnabled = strengthLevel.contains("Bulletproof")
        strength_level.text = strengthLevel
        strength_level.setTextColor(ContextCompat.getColor(this,color))
        strength_level_indicator.setBackgroundColor(ContextCompat.getColor(this,color))

    }
}
