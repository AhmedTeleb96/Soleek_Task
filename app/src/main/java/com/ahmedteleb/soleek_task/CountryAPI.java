package com.ahmedteleb.soleek_task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryAPI
{

    @GET("rest/v2/all")
    Call<ArrayList<Country>> getCountries();
}
