package com.example.caforgst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caforgst.Api.ApiClient;
import com.example.caforgst.Api.Apiserver;
import com.example.caforgst.VerificationnoActivity.VerifyNumber;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    Button submitbtn;
    EditText etNumber;
    TextView skip;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        submitbtn=findViewById(R.id.submitbtn);
        etNumber=findViewById(R.id.etNumber);
        skip=findViewById(R.id.skip);

        progressDialog=new ProgressDialog(this);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserMobileNumber();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SigninActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void UserMobileNumber() {

        String no = etNumber.getText().toString();


            if(no.isEmpty())
            {
                Toast.makeText(SigninActivity.this,"Filed is not empty",Toast.LENGTH_SHORT).show();
            }
            else if(no.length()>10||no.length()<10) {

                showError(etNumber, "Your number is not valid");
            }


            else
             {
                progressDialog.setMessage("Verifying number...");
                progressDialog.show();
                HashMap<String, String> num = new HashMap<String, String>();
                num.put("mobile",no);

                Apiserver apiServer = ApiClient.getClient().create(Apiserver.class);

                Call<VerifyNumber> call = apiServer.verifynumber(num);

                call.enqueue(new Callback<VerifyNumber>() {
                    @Override
                    public void onResponse(Call<VerifyNumber> call, Response<VerifyNumber> response) {

                        if (response.body().getCode() == 200) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Successfully Verified!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SigninActivity.this,VerifyCodeActivity.class);
                        intent.putExtra("number",no);
                        startActivity(intent);

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

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

}