package com.example.aitarot20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.os.CountDownTimer
import android.text.Editable
import com.example.aitarot20.common.SimpleTextWatcher

class VerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val phoneNumber = intent.getStringExtra("PHONE_NUMBER")
        val verificationMessage: TextView = findViewById(R.id.verificationMessage)
        verificationMessage.text = "验证码已发送至+86 $phoneNumber"

        val countdownTimer: TextView = findViewById(R.id.countdownTimer)
        val resendText: TextView = findViewById(R.id.resendText)

        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownTimer.text = "${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                countdownTimer.text = ""
                resendText.setTextColor(resources.getColor(R.color.blue_highlight))
            }
        }.start()

        setupCodeInput()
    }

    private fun setupCodeInput() {
        val editTexts = listOf(
            findViewById<EditText>(R.id.verificationCode1),
            findViewById<EditText>(R.id.verificationCode2),
            findViewById<EditText>(R.id.verificationCode3),
            findViewById<EditText>(R.id.verificationCode4),
            findViewById<EditText>(R.id.verificationCode5),
            findViewById<EditText>(R.id.verificationCode6)
        )

        for (i in editTexts.indices) {
            editTexts[i].addTextChangedListener(object : SimpleTextWatcher() {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 && i < editTexts.size - 1) {
                        editTexts[i + 1].requestFocus()
                    } else if (s?.isEmpty() == true && i > 0) {
                        editTexts[i - 1].requestFocus()
                    }
                }
            })
        }
    }
}
