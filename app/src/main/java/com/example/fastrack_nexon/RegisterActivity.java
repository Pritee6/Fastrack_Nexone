package com.example.fastrack_nexon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister, btnCallBack;
    private TextInputEditText edtName, edtPhoneNumber, edtPassword;
    private TextView txtSignUp, txtOTPVerification;
    private DatabaseHelper DB;
    private Spinner spinnerCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);
        btnCallBack = findViewById(R.id.btnCallBack);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);
        txtSignUp = findViewById(R.id.txtSignUp);
        txtOTPVerification = findViewById(R.id.txtOTPVerification);
        DB = new DatabaseHelper(this);

        spinnerCountries = findViewById(R.id.spinnerCountries);
        spinnerCountries.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String phoneNumber = edtPhoneNumber.getText().toString();
                String password = edtPassword.getText().toString();

                if(name.equals("")||phoneNumber.equals("")||password.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                else{
                        Boolean checkuser = DB.checkusernamepassword(phoneNumber, password);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(phoneNumber, password);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "User already exists! Please sign in", Toast.LENGTH_LONG).show();
                            }
                        } else
                            {
                                Toast.makeText(RegisterActivity.this, "User already exists! Please sign in", Toast.LENGTH_LONG).show();
                            }
                }
            }
        });

        txtOTPVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String areaCode = CountryData.code[spinnerCountries.getSelectedItemPosition()];

                String number = edtPhoneNumber.getText().toString().trim();

                if (number.isEmpty() || number.length()<10)
                {
                    Toast.makeText(RegisterActivity.this,"Invalid Phone Number",Toast.LENGTH_LONG).show();
                    edtPhoneNumber.requestFocus();
                    return;
                }

                String phoneNumber = "+" + areaCode + number;
                Intent intent = new Intent(RegisterActivity.this, OTPActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });
    }

   @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
