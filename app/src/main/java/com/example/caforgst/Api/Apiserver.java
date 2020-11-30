package com.example.caforgst.Api;

import com.example.caforgst.RegistrationApi.UserRegistration;
import com.example.caforgst.VerificationcodeActivity.VerifyCode;
import com.example.caforgst.VerificationnoActivity.VerifyNumber;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Apiserver {

    @FormUrlEncoded
    @Headers({"Accept-Encoding: identity"})
    @POST("auth/otp/send")
    Call<VerifyNumber> verifynumber(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @Headers({"Accept-Encoding: identity"})
    @POST("auth/otp/verify")
    Call<VerifyCode> verifycode(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @Headers({"Accept-Encoding: identity"})
    @POST("auth/register")
    Call<UserRegistration> register(@FieldMap HashMap<String, String> params, @Field("mobile") String phoneNumber);
}
