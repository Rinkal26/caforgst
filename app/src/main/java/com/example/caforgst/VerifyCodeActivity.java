package com.example.caforgst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caforgst.Api.ApiClient;
import com.example.caforgst.Api.Apiserver;
import com.example.caforgst.VerificationnoActivity.VerifyNumber;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyCodeActivity extends AppCompatActivity {

    Button continuebtn;
    TextView textfornumber;
    String no;
    ProgressDialog progressDialog;
    EditText otpedittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        continuebtn = findViewById(R.id.continuebtn);
        textfornumber = findViewById(R.id.textfornumber);

        otpedittext = findViewById(R.id.et_otp);
        textfornumber = findViewById(R.id.textfornumber);
        progressDialog = new ProgressDialog(this);


        no = getIntent().getExtras().getString("number");
        textfornumber.setText("Enter verication code send to your +91" + no);

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressDialog.setMessage("Verifying code please wait..");
                progressDialog.show();

                String otpet = otpedittext.getText().toString();

                if (otpet.isEmpty()) {
                    showError(otpedittext, "Please enter all fields");
                }
                else {

                    Apiserver apiServer = ApiClient.getClient().create(Apiserver.class);


                    HashMap<String, String> otp = new HashMap<String, String>();
                    otp.put("otp", otpet);
                    otp.put("mobile", no);

                    Call<VerifyNumber> call = apiServer.verifynumber(otp);

                    call.enqueue(new Callback<VerifyNumber>() {
                        @Override
                        public void onResponse(Call<VerifyNumber> call, Response<VerifyNumber> response) {
                            progressDialog.hide();

                            if (response.body().getCode() == 200) {

                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Successfully verified!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(VerifyCodeActivity.this, RegistrationActivity.class);
                                startActivity(intent);

                            } else {

                                progressDialog.dismiss();
                                Toast.makeText(VerifyCodeActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<VerifyNumber> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}
