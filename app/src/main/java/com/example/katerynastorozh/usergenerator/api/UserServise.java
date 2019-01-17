package com.example.katerynastorozh.usergenerator.api;

import com.example.katerynastorozh.usergenerator.util.Result;
import com.example.katerynastorozh.usergenerator.util.UserItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserServise {
    @GET("?results=20")
    Call<Result> getListUsers();
}
