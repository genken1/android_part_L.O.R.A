package com.creaters.lora.MainMenu.FacebookOAuth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.creaters.lora.MainMenu.MenuActivity;
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
    private Activity activity;

    public OAuthFB(){}

    public OAuthFB(Context context, LoginButton loginButton, CallbackManager callbackManager){
        this.callbackManager = callbackManager;
        this.loginButton = loginButton;
        this.context = context;
        user = new User();
        facebookOAuth();
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null){
                Toast.makeText(context, "User logged out", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, user.getName()+" "+user.getLast_name()+" "+user.getEmail()+" ", Toast.LENGTH_LONG).show();
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    user.setName(object.getString("first_name"));
                    user.setLast_name(object.getString("last_name"));
                    user.setEmail(object.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name, last_name, email, id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken()!=null){
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }
    private void facebookOAuth() {
        loginButton.setPermissions(Arrays.asList("email", "public_profile"));
        checkLoginStatus();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
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
        LoginManager.getInstance().logInWithReadPermissions((Activity)context, Arrays.asList("public_profile"));
    }
}
