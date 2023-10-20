package com.gl4.tp1pizzaapp

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.gl4.tp1pizzaapp.R.*
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText

//Put your phone in line 70

class OrderActivity : AppCompatActivity() {
    lateinit var nomInput: TextInputEditText
    lateinit var prenomInput: TextInputEditText
    lateinit var adresseInput: TextInputEditText
    lateinit var miniRadioButton: RadioButton
    lateinit var moyenneRadioButton: RadioButton
    lateinit var maxiRadioButton: RadioButton
    lateinit var fromageChip: Chip
    lateinit var tomateChip: Chip
    lateinit var champignonChip: Chip
    lateinit var thonChip: Chip
    lateinit var commanderBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_order)

        nomInput = findViewById(id.textFieldNom)
        prenomInput = findViewById(id.textFieldPrenom)
        adresseInput = findViewById(id.textFieldAdresse)

        miniRadioButton = findViewById(id.radio_button_Mini)
        moyenneRadioButton = findViewById(id.radio_button_Moyenne)
        maxiRadioButton = findViewById(id.radio_button_Maxi)

        fromageChip = findViewById(id.chipFromage)
        champignonChip = findViewById(id.chipChampignon)
        tomateChip = findViewById(id.chipTomate)
        thonChip = findViewById(id.chipThon)

        commanderBtn = findViewById(id.btnCommander)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 111)

        }


        commanderBtn.setOnClickListener {

            if((!miniRadioButton.isChecked && !maxiRadioButton.isChecked && !moyenneRadioButton.isChecked)
                || nomInput.text.toString().isEmpty()
                || prenomInput.text.toString().isEmpty()
                || adresseInput.text.toString().isEmpty()){
                val toast = Toast.makeText(applicationContext, "Tous les champs sont obligatoires", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                var message : String = "Nom et prénom : ${nomInput.text.toString()} ${prenomInput.text.toString()} \n" +
                        "Adresse : ${adresseInput.text.toString()} \n" +
                        "Taille : ${taillePizza()} \n" +
                        "Ingredients : ${ingredientsPizza()}"
                //Put your phone number here
                sendMessage("+21625894980", message)

                val toast = Toast.makeText(applicationContext, "Commande envoyée", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


        }

    }

    private fun sendMessage(num: String, message: String){
        var sms : SmsManager = SmsManager.getDefault()
        println(num)
        sms.sendTextMessage(num,"Le Pizzas",message, null,null)
        println("completed")
    }

    private fun taillePizza() : String{
        if(miniRadioButton.isChecked){
            return "Mini"
        }
        else if(maxiRadioButton.isChecked){
            return "Maxi"
        }
        else
            return "Moyenne"
    }

    private fun ingredientsPizza() : String {
        var ing :String = ""
        if(fromageChip.isChecked){
            ing+= "Fromage "
        }
        if(champignonChip.isChecked){
            ing+= "Champignon "
        }
        if(tomateChip.isChecked){
            ing+= "Tomate "
        }
        if(thonChip.isChecked){
            ing+= "Thon"
        }
        return ing
    }
}