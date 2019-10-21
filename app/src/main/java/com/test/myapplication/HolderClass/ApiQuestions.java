package com.test.myapplication.HolderClass;

import com.test.myapplication.Model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ApiQuestions {

    @GET("questions/getquestions.php")
    Call<List<Question>> getAllQuestions();


    @GET("questions/getquestions.php/{id_question}")
    Call<Question> getQuestion(@Field("id_question") int id);

}
