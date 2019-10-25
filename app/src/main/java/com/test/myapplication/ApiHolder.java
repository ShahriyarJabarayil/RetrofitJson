package com.test.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiHolder {

    @GET("signup.php/GET")
    Call<List<UsersModel>> getUsers();





    @FormUrlEncoded
    @POST("signup.php/POST")
    Call<UsersModel> postUser(
        @Field("name") String name,
        @Field("email") String email,
        @Field("pwd") String password,
        @Field("status") String status);


    @FormUrlEncoded
    @POST("userupdate.php/POST/{id}")
    Call<UsersModel> updateUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("pwd") String password,
            @Field("status") String status);



    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") int id);


}
