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

        val textViewMessage = findViewById<TextView>(R.id.textViewMessage)
        textViewMessage.text = message

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
