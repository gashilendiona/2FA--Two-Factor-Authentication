   Two-Factor Authentication (2FA) App

                               
This Android application implements a two-factor authentication (2FA) system using email-based One-Time Passwords (OTPs). It demonstrates secure login workflows by verifying a user's identity through OTP validation.

Features

•	Email-based OTP generation and delivery: Sends secure OTPs to user emails via Gmail SMTP.

•	OTP verification workflow: Validates user input against generated OTPs.

•	Seamless user experience: Includes login, OTP verification, and home navigation screens.

•	Resend functionality: Allows users to request a new OTP if needed.

•	Error handling: Provides feedback for invalid inputs and delivery issues.



Key Components
1. MainActivity: Accepts user email input and triggers OTP generation and delivery.
   
2. EmailSender: Handles OTP creation and email transmission using Gmail SMTP.
   
3. OTPActivity: Validates the OTP entered by the user, manages resend requests, and transitions to the home screen upon success.
   
4. HomeActivity: A placeholder home screen after successful login.
   
5. OTPCallback: An interface to handle asynchronous feedback for OTP delivery.



Technologies Used
- JavaMail API: For sending OTP emails.
- Android SDK: For building the UI and managing app functionality.

For the full XML layout implementation, refer to the res/layout directory in the project files.
