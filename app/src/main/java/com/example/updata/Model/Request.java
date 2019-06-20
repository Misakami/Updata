package com.example.updata.Model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Request {

    /**
     * id_str : 841811988804190208
     * name : Cardinals Trump
     * screen_name : CardinalsTrump
     * protected : false
     * location : Busch Stadium
     * description : #MakeCardinalsGreatAgain
     * followers_count : 4571
     * friends_count : 2056
     * listed_count : 30
     * created_at : Wed Mar 15 00:43:13 +0000 2017
     * favourites_count : 822
     * verified : false
     * statuses_count : 331
     * time_zone : null
     * lang : null
     * profile_image_url : http://pbs.twimg.com/profile_images/841812428904136706/j3SfkgZ-_400x400.jpg
     */

    private String id_str;
    private String name;
    private String screen_name;
    @SerializedName("protected")
    private boolean protectedX;
    private String location;
    private String description;
    private int followers_count;
    private int friends_count;
    private int listed_count;
    private String created_at;
    private int favourites_count;
    private boolean verified;
    private int statuses_count;
    private String profile_image_url;

    public static Request objectFromData(String str) {

        return new Gson().fromJson(str, Request.class);
    }

    public static Request objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Request.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Request> arrayRequestFromData(String str) {

        Type listType = new TypeToken<ArrayList<Request>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Request> arrayRequestFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Request>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public boolean isProtectedX() {
        return protectedX;
    }

    public void setProtectedX(boolean protectedX) {
        this.protectedX = protectedX;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public int getListed_count() {
        return listed_count;
    }

    public void setListed_count(int listed_count) {
        this.listed_count = listed_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }
}
