package com.example.detyra_2fa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.email_input);
        Button sendOtpButton = findViewById(R.id.login_button);

        sendOtpButton.setOnClickListener(v -> {
            String enteredEmail = emailField.getText().toString().trim();

            if (TextUtils.isEmpty(enteredEmail)) {
                Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_SHORT).show();
                return;
            }

            String otpCode = EmailSender.createOTP();
            EmailSender.sendOTP(enteredEmail, otpCode, success -> {
                if (success) {
                    Log.d("MainActivity", "OTP sent successfully to " + enteredEmail);
                    Intent intent = new Intent(this, OTPActivity.class);
                    intent.putExtra("otp_key", otpCode);
                    intent.putExtra("email", enteredEmail);
                    startActivity(intent);
                } else {
                    Log.e("MainActivity", "Failed to send OTP.");
                    Toast.makeText(this, "Failed to send OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
