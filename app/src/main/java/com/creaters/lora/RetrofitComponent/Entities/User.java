package com.creaters.lora.RetrofitComponent.Entities;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/*In this case we use annotation SerializedName
*which contains name variable in json file.
* For example, json file:
*                           {
*                               "id":1,
*                               "name": "Nick",
*                               "lastName":"Neck",
*                               "email":"nicknek@mail.com"
*                           }
* As you can see we have a similar name in json and annotation*/

public class User {
    /*@Nullable
    @SerializedName("id")
    Integer id;*/

    @SerializedName("name")
    String name;

    @SerializedName("last_name")
    String last_name;

    @SerializedName("email")
    String email;

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
