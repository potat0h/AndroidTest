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

        val name = intent.getStringExtra("NAME") ?: "User"
        val message = "$name, uspješno ste došli do 10 koraka."

        val textViewMessage = findViewById<TextView>(R.id.textViewMessage)
        textViewMessage.text = message

        val buttonSendSMS = findViewById<Button>(R.id.buttonSendSMS)
        buttonSendSMS.setOnClickListener {
            val phoneNumber = "123456789"  // Zamijeni sa svojim brojem
            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phoneNumber")
                putExtra("sms_body", message)
            }
            startActivity(smsIntent)
        }
    }
}
