package com.example.clase24septiembre

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var heightSeekBar: SeekBar
    private lateinit var heightValue: TextView
    private lateinit var weightValue: TextView
    private lateinit var ageValue: TextView
    private lateinit var maleCard: CardView
    private lateinit var femaleCard: CardView
    private var weight: Int = 55
    private var age: Int = 22
    private var gender: String = "Male" // Default gender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa las vistas
        heightSeekBar = findViewById(R.id.height_seekbar)
        heightValue = findViewById(R.id.height_value)
        weightValue = findViewById(R.id.weight_value)
        ageValue = findViewById(R.id.age_value)
        maleCard = findViewById(R.id.male_card)
        femaleCard = findViewById(R.id.female_card)

        // Establece el valor inicial de la altura
        heightValue.text = "${heightSeekBar.progress} Cm"

        // Configura el listener para el SeekBar de altura
        heightSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                heightValue.text = "${progress} Cm"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Configura los botones de peso
        findViewById<Button>(R.id.increase_weight).setOnClickListener {
            weight++
            updateWeightDisplay()
        }
        findViewById<Button>(R.id.decrease_weight).setOnClickListener {
            if (weight > 0) weight--
            updateWeightDisplay()
        }

        // Configura los botones de edad
        findViewById<Button>(R.id.increase_age).setOnClickListener {
            age++
            updateAgeDisplay()
        }
        findViewById<Button>(R.id.decrease_age).setOnClickListener {
            if (age > 0) age--
            updateAgeDisplay()
        }

        // Configura los CardViews de género
        maleCard.setOnClickListener {
            gender = "Male"
            maleCard.setCardBackgroundColor(resources.getColor(R.color.selected_color))
            femaleCard.setCardBackgroundColor(resources.getColor(R.color.default_color))
        }

        femaleCard.setOnClickListener {
            gender = "Female"
            femaleCard.setCardBackgroundColor(resources.getColor(R.color.selected_color))
            maleCard.setCardBackgroundColor(resources.getColor(R.color.default_color))
        }

        // Configura el botón de calcular BMI
        findViewById<Button>(R.id.calculate_bmi).setOnClickListener {
            val height = heightSeekBar.progress
            val bmi = calculateBMI(weight, height)
            // Mostrar el resultado de BMI
            Toast.makeText(this, "Your BMI is ${"%.2f".format(bmi)}\nGender: $gender", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateWeightDisplay() {
        weightValue.text = "$weight"
    }

    private fun updateAgeDisplay() {
        ageValue.text = "$age"
    }

    private fun calculateBMI(weight: Int, height: Int): Float {
        return if (height > 0) {
            weight / ((height / 100f) * (height / 100f))
        } else {
            0f
        }
    }
}

