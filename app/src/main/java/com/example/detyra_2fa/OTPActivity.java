package com.example.detyra_2fa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OTPActivity extends AppCompatActivity {
    private EditText inputOTP;
    private String originalOTP;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        inputOTP = findViewById(R.id.otp_field);
        Button buttonVerify = findViewById(R.id.verify_button);
        Button buttonResend = findViewById(R.id.resend_button);

        originalOTP = getIntent().getStringExtra("otp_key");
        email = getIntent().getStringExtra("email");

        Log.d("OTPActivity", "Received OTP: " + originalOTP + ", Email: " + email);

        buttonVerify.setOnClickListener(v -> {
            String enteredOTP = inputOTP.getText().toString();

            if (TextUtils.isEmpty(enteredOTP)) {
                Toast.makeText(this, "Please enter the OTP.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (enteredOTP.equals(originalOTP)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Incorrect code. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonResend.setOnClickListener(v -> {
            originalOTP = EmailSender.createOTP();
            EmailSender.sendOTP(email, originalOTP, success -> {
                if (success) {
                    Toast.makeText(this, "A new OTP has been sent to your email.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to resend OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
