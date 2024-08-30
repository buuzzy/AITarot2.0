package com.example.aitarot20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        val phoneNumberEditText: EditText = findViewById(R.id.phoneNumberEditText)
        val nextButton: Button = findViewById(R.id.loginButton)

        nextButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString().trim()

            if (isValidPhoneNumber(phoneNumber)) {
                // 传递手机号到第三个页面
                val intent = Intent(this, VerificationActivity::class.java)
                intent.putExtra("PHONE_NUMBER", phoneNumber)
                startActivity(intent)
            } else {
                // 显示提示信息
                Toast.makeText(this, "请输入有效的手机号", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // 检查手机号是否为11位数字且以1开头
        return phoneNumber.matches(Regex("^1[0-9]{10}$"))
    }
}
