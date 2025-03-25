package com.example.androidtest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val name = intent.getStringExtra("NAME") ?: "User"
        val message = getString(R.string.success, name)

        val databaseHelper = DatabaseHelper(this)
        val successfulUsers = databaseHelper.getSuccessfulUsers()
        val textViewMessage = findViewById<TextView>(R.id.textViewMessage)
        val textViewMessageDown = findViewById<TextView>(R.id.textViewMessageDown)

        val buttonDeleteData = findViewById<Button>(R.id.buttonDeleteData)
        buttonDeleteData.setOnClickListener {
            databaseHelper.deleteAllData()
            textViewMessage.text = ""
            textViewMessageDown.text = getString(R.string.empty)
        }

        textViewMessage.text = successfulUsers.joinToString(", ") { it }

        if(databaseHelper.isDatabaseEmpty()){
            textViewMessageDown.text = getString(R.string.empty)
        }

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupPhoneNumbers)
        val buttonSendSMS = findViewById<Button>(R.id.buttonSendSMS)
        buttonSendSMS.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val radioButton = findViewById<RadioButton>(selectedId)
                val phoneNumber = radioButton.text.toString()
                val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:$phoneNumber")
                    putExtra("sms_body", message)
                }
                startActivity(smsIntent)
            }
        }
    }
}
