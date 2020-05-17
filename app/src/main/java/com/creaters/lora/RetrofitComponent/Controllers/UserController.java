package com.creaters.lora.RetrofitComponent.Controllers;

import android.util.Log;

import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Entities.User;
import com.creaters.lora.RetrofitComponent.Services.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*Use enqueue for Asynchronously send the request
 *and notify callback of its response or if an error occurred talking to the server,
 *creating the request, or processing the response.
 */
public class UserController {
    final String ERROR= "ERROR CODE";
    public static final String BASE_URL = "https://serverlora.herokuapp.com/";
    private UserService service;
    private Retrofit retrofit;

    public UserController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UserService.class);
    }

    /*Get email request and send data to preferences*/
    public Call createGetRequest(Preferences preferences, String email) {
        Call<User> call = service.getUserByEmail(email);
        //Могут быть проблемы, из-за асинхронности(Данные будут извлекаться быстрее, чем класться)
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;
                }
                String id = response.body().getId().toString();
                preferences.setValue("id", id);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("fail");
                //error handling
            }
        });
        return call;
    }

    /*Get id request*/
    public void createGetRequest(Integer id) {
        Call<User> call;
        call = service.getUserById(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Log.e(ERROR, String.valueOf(response.code()));
                    return;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //error handling
            }
        });
    }

    /*Post request*/
    public void createPostRequest(User user) {
        String param = jsonUserConverter(user).toString();
        Call<String> call = service.create(param);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.e(ERROR, String.valueOf(response.code()));
                    return;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //error handling
            }
        });

    }

    public JSONObject jsonUserConverter(User user) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", user.getName());
            jsonObject.put("last_name", user.getLast_name());
            jsonObject.put("email", user.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
