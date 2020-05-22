package com.creaters.lora.MainMenu.FacebookOAuth;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Controllers.AchievementsController;
import com.creaters.lora.RetrofitComponent.Controllers.UserController;
import com.creaters.lora.RetrofitComponent.Entities.User;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class OAuthFB {
    private Context context;
    private CallbackManager callbackManager;
    private AccessToken accessToken = AccessToken.getCurrentAccessToken();
    private LoginButton loginButton;
    private User user;
    private UserController userController;
    private Preferences preferences;
    private SyncRequest sync;

    public OAuthFB(Context context, LoginButton loginButton, CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
        this.loginButton = loginButton;
        this.context = context;
        user = new User();
        preferences = new Preferences(context, "user_data");
        facebookOAuth();
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                Toast.makeText(context, "User logged out", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, user.getName() + " " + user.getLast_name() + " " + user.getEmail() + " ", Toast.LENGTH_LONG).show();
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    userController = new UserController();
                    /*в поле с именем хранится еще и фаимлия поэтому приходится делать такую заглушку.*/
                    String[] result = object.getString("name").split(" ", 2);
                    user.setName(result[0]);
                    user.setLast_name(object.getString("last_name"));
                    /**
                    *добавляем данные в SharedPreferences(Возможна исключительная ситуация,
                    *когда у пользователя к аккаунту facebook не привзяана почта)
                    */
                    try {
                        user.setEmail(object.getString("email"));
                        preferences.setValue("email", object.getString("email"));

                        sync = new SyncRequest(preferences, userController, user);
                        sync.start();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name, last_name, email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null) {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    private void facebookOAuth() {
        loginButton.setPermissions(Arrays.asList("email", "public_profile"));
        checkLoginStatus();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(context, "We could not authorize you", Toast.LENGTH_LONG).show();

            }
        });
        LoginManager.getInstance().logInWithReadPermissions((Activity) context, Arrays.asList("public_profile"));
    }
}
