package com.creaters.lora.RetrofitComponent.Entities;

import com.google.gson.annotations.SerializedName;

/*In this case we use annotation SerializedName
 *which contains name variable in json file.
 * For example, json file:
 *                           {
 *                               "id":1,
 *                               "name": "bird",
 *                               "url":"https://drive.google.com/open?id=1UaIC4wPq87uU34ak10t-nB3fFbNt9Hiz",
 *                           }
 * As you can see we have a similar name in json and annotation*/

public class Achievements {
    public Achievements(){}

    public Achievements(Integer id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
