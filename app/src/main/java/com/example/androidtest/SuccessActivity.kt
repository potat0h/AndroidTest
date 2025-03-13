package com.example.androidtest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val isEnglish = intent.getBooleanExtra("IS_ENGLISH", true)
        val name = intent.getStringExtra("NAME") ?: "User"
        val message = if (isEnglish)
            "$name, you have successfully reached 10 steps."
        else
            "$name, uspješno ste došli do 10 koraka."

        findViewById<TextView>(R.id.textViewMessage).text = message

        val buttonSendSMS = findViewById<Button>(R.id.buttonSendSMS)
        buttonSendSMS.text = if (isEnglish) "Send SMS" else "Pošalji SMS"
        buttonSendSMS.setOnClickListener {
            val phoneNumber = "0957918021" // Zamijenite sa svojim brojem
            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phoneNumber")
                putExtra("sms_body", message)
            }
            startActivity(smsIntent)
        }
    }
}
