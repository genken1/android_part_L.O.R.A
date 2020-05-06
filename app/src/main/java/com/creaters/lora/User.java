package com.creaters.lora;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("lastName")
    String last_name;
}
