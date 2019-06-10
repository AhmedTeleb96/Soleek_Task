package com.ahmedteleb.soleek_task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesListActivity extends AppCompatActivity {

    private Button logout;

    private ProgressBar progressBar;
    private ListView listCountries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        listCountries = (ListView) findViewById(R.id.list_Countries);
        getCountriesOnline();

        logout =findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signOut();
            }
        });
    }

    private void getCountriesOnline() {
        // using retrofit API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(CountryAPI.class).getCountries().enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                progressBar.setVisibility(View.GONE);
                showFlowers(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                Toast.makeText(CountriesListActivity.this, "Error In Connection", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showFlowers(ArrayList<Country> Countries) {
        Log.d("API", "Countries # " + Countries.size());

        CountryAdapter adapter = new CountryAdapter(Countries);
        listCountries.setAdapter(adapter);

    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(CountriesListActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        return;

    }
}
