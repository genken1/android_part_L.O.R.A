package com.creaters.lora;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button viewAllButton = (Button) findViewById(R.id.viewAllButton);
        final TextView allBooks = (TextView) findViewById(R.id.allBooks);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://serverlora.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserService service = retrofit.create(UserService.class);

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<User>> createCall = service.all();
                createCall.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> _, Response<List<User>> resp) {
                        allBooks.setText("ALL USERS by NAME:\n");
                        for (User us : resp.body()) {
                            allBooks.append(us.id+" "+us.name+" "+us.last_name + "\n");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> _, Throwable t) {
                        t.printStackTrace();
                        allBooks.setText(t.getMessage());
                    }
                });
            }
        });
    }
}
