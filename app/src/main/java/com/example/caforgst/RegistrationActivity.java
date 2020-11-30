package com.example.caforgst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caforgst.Api.ApiClient;
import com.example.caforgst.Api.Apiserver;
import com.example.caforgst.Helper.Sharedpref;
import com.example.caforgst.RegistrationApi.UserRegistration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    Spinner gender,city,state;
    Button submitbtn;
    EditText firstname,lastname,emailaddress,dob,pincode;
    ImageView calender;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ProgressDialog progressDialog;
    Sharedpref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        emailaddress=findViewById(R.id.emailaddress);
        dob=findViewById(R.id.dob);
        pincode=findViewById(R.id.pincode);
        gender=findViewById(R.id.gender);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        submitbtn=findViewById(R.id.submitbtn);
        calender=findViewById(R.id.calender);

        sharedpref=new Sharedpref(this);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == calender) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this, R.style.MyDatePickerDialogTheme,new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }

            }
        });

        progressDialog = new ProgressDialog(this);

        String[] value1 = {"Gender","Male","Female"};
        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(value1));
        ArrayAdapter<String> arrayAdaptername1 = new ArrayAdapter<>(this, R.layout.spinner_gender, arrayList1);
        gender.setAdapter(arrayAdaptername1);

        String[] value2 = {"City", "Adilabad","Agra","Ahmedabad","Ahmednagar","Aizawl","Ajitgarh (Mohali)","Ajmer","Akola","Alappuzha","Aligarh","Alirajpur","Almora","Alwar","Ambala"," Ambedkar Nagar","Amravati","Amreli district","Amritsar",
                "Anand","Anantapur","Anantnag","Angul","Anjaw","Anuppur","Araria","Ariyalur","Arwal","Ashok Nagar","Auraiya","Aurangabad","Azamgarh","Badgam","Bagalkot", "Bageshwar", "Bagpat",
                "Bahraich","Baksa","Balaghat","Balangir","Balasore","Ballia","Balrampur","Banaskantha","Banda","Bandipora","Bangalore Rural","Bangalore Urban","Banka","Bankura",
                "Banswara","Barabanki","Baramulla" ,"Baran", "Bardhaman","Bareilly", "Bargarh (Baragarh)", "Barmer", "Barnala", "Barpeta", "Barwani", "Bastar", "Basti", "Bathinda", "Beed",
                "Begusarai" ,"Belgaum" ,"Bellary" ,"Betul" ,"Bhadrak","Bhagalpur","Bhandara","Bharatpur","Bharuch","Bhavnagar","Bhilwara","Bhind","Bhiwani","Bhojpur", "Bhopal", "Bidar", "Bijapur",
                "Bijapur" ,"Bijnor" ,"Bikaner", "Bilaspur", "Birbhum", "Bishnupur", "Bokaro", "Bongaigaon", "Boudh (Bauda)", "Budaun","Bulandshahr","Buldhana","Bundi","Burhanpur", "Buxar", "Cachar",
                "Central Delhi","Chamarajnagar", "Chamba","Chamoli","Champawat","Champhai","Chandauli","Chandel","Chandigarh","Chandrapur","Changlang","Chatra","Chennai","Chhatarpur", "Chhatrapati Shahuji Maharaj Nagar",
                "Chhindwara","Chikkaballapur", "Chikkamagaluru", "Chirang", "Chitradurga", "Chitrakoot", "Chittoor", "Chittorgarh", "Churachandpur", "Churu", "Coimbatore", "Cooch Behar", "Cuddalore",
                "Cuttack",
                "Dadra and Nagar Haveli",
                "Dahod",
                "Dakshin Dinajpur",
                "Dakshina Kannada",
                "Daman",
                "Damoh",
                "Dantewada",
                "Darbhanga",
                "Darjeeling",
                "Darrang",
                "Datia",
                "Dausa",
                "Davanagere",
                "Debagarh (Deogarh)",
                "Dehradun",
                "Deoghar",
                "Deoria",
                "Dewas",
                "Dhalai",
                "Dhamtari",
                "Dhanbad",
                "Dhar",
                "Dharmapuri",
                "Dharwad",
                "Dhemaji",
                "Dhenkanal",
                "Dholpur",
                "Dhubri",
                "Dhule",
                "Dibang Valley",
                "Dibrugarh",
                "Dima Hasao",
             };
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(value2));
        ArrayAdapter<String> arrayAdaptername = new ArrayAdapter<>(this, R.layout.spinner_city, arrayList2);
        city.setAdapter(arrayAdaptername);

        String[] value3 = {"State","Andhra Pradesh (AP)", "Arunachal Pradesh (AR)","Assam (AS)","Bihar (BR)","Chhattisgarh (CG)","Goa (GA)","Gujarat (GJ)", "Haryana (HR)", "Himachal Pradesh (HP)","Jammu and Kashmir (JK)"," Jharkhand (JH)","Karnataka (KA)"," Kerala (KL)"," Madhya Pradesh (MP)"," Maharashtra (MH)"," Manipur (MN)","Meghalaya (ML)","  Mizoram (MZ)", "Nagaland (NL)","Odisha(OR)"," Punjab (PB)",
                " Rajasthan (RJ)","Sikkim (SK)","Tamil Nadu (TN)","Tamil Nadu (TN)"," Telangana (TS)"," Tripura (TR)"," Uttar Pradesh (UP)"," Uttarakhand (UK)","West Bengal (WB)"};
        ArrayList<String> arrayList3 = new ArrayList<>(Arrays.asList(value3));
        ArrayAdapter<String> arrayAdaptername3 = new ArrayAdapter<>(this, R.layout.spinner_state, arrayList3);
        state.setAdapter(arrayAdaptername3);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegistration();
            }
        });
    }

    private void UserRegistration() {

        String fname=firstname.getText().toString();
        String lname=lastname.getText().toString();
        String email=emailaddress.getText().toString();
        String dateofbirth=dob.getText().toString();
        String usergender=gender.getSelectedItem().toString();
        String userstate=state.getSelectedItem().toString();
        String usercity=city.getSelectedItem().toString();
        String userpincode=pincode.getText().toString();


        if(fname.isEmpty()||lname.isEmpty()||email.isEmpty()||dateofbirth.isEmpty()||userpincode.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
           }
           else if(fname.length()>20)
            {
                showError(firstname,"Name is too long");
            }
            else if(lname.length()>20)
            {
                showError(lastname,"Name is too long");
            }

            else if(!email.contains("@")||!email.contains(".com"))
            {
                showError(emailaddress,"Email is not valid");
            }

             else if(dateofbirth.isEmpty())
            {
                showError(dob,"Enter date of birth");
            }

             else if(userpincode.length()>6)
             {
                showError(pincode,"Enter valid pincode");
             }

            else
            {

            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Verifying...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            Apiserver apiserver= ApiClient.getClient().create(Apiserver.class);

            HashMap<String,String> reg = new HashMap<String,String>();
            reg.put("first_name",fname);
            reg.put("last_name",lname);
            reg.put("email",email);
            reg.put("dob",dateofbirth);
            reg.put("gender",usergender);
            reg.put("state",userstate);
            reg.put("city",usercity);
            reg.put("pin",userpincode);

            Call<UserRegistration> registration =apiserver.register(reg,sharedpref.getnumber());

            registration.enqueue(new Callback<UserRegistration>() {
                @Override
                public void onResponse(Call<UserRegistration> call, Response<UserRegistration> response) {


                    if (response.body().getCode()==200) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Successfully verified!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegistrationActivity.this,ITRActivity.class);
                        startActivity(intent);

                    }
                    else{

                        Toast.makeText(RegistrationActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                    }
                }
                @Override
                public void onFailure(Call<UserRegistration> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RegistrationActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}