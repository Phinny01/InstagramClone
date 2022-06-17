package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

  public class SignUp extends AppCompatActivity {
    EditText SignUpUsername;
    EditText SignUpPassword;
    Button BtnSubmit;
    String message = "Something went wrong";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SignUpUsername = findViewById(R.id.user);
        SignUpPassword = findViewById(R.id.Password);
        BtnSubmit = findViewById(R.id.signup);

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the ParseUser
                ParseUser user= new ParseUser();

                //getting text from editText
                String username = SignUpUsername.getText().toString(); // gets text and changes it to string
                String password = SignUpPassword.getText().toString();

                // Set core properties
                user.setUsername(username); //setting the username to username inputted by user
                user.setPassword(password); // setting the password to password inputted by user

                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {
                            goMainActivity();
                        } else {
                            Toast.makeText(SignUp.this, message,Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }

    private void goMainActivity() {
        Intent intent = new Intent(SignUp.this,MainActivity.class);
        startActivity((intent));
    }
}