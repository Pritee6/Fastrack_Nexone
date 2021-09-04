package com.example.fastrack_nexon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextView txtPhoneNumber;
    private TextInputEditText edtNewPassword, edtReEnterPassword;
    private Button btnConfirm;
    private DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtReEnterPassword = findViewById(R.id.edtReEnterPassword);
        btnConfirm = findViewById(R.id.btnConfirm);
        DB = new DatabaseHelper(this);

        Intent intent = getIntent();
        txtPhoneNumber.setText(intent.getStringExtra("phoneNumber"));

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = txtPhoneNumber.getText().toString();
                String newPassword = edtNewPassword.getText().toString();
                String reEnterPassword = edtReEnterPassword.getText().toString();

                if(phoneNumber.equals("")||newPassword.equals("")||reEnterPassword.equals(""))
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(newPassword.equals(reEnterPassword)){
                        Boolean checkPasswordUpdate = DB.updatePassword(phoneNumber,newPassword);
                        if(checkPasswordUpdate==true){
                                Toast.makeText(ForgotPasswordActivity.this, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(ForgotPasswordActivity.this, "Password Updation Failed", Toast.LENGTH_SHORT).show();
                            }
                    }else{
                        Toast.makeText(ForgotPasswordActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}