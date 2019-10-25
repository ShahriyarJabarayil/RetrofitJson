package com.test.myapplication.HolderClass;

import com.test.myapplication.Model.Answer;
import com.test.myapplication.Model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiJsonQuiz {

    @GET("questions/getquestions.php/GET")
    Call<List<Question>> getAllQuestions();


    @GET("questions/getquestions.php")
    Call<List<Question>> getQuestion1(@Query("id_question") int id);


    @GET("questions/selectquestion.php")
    Call<Question> getQuestion(@Query("id_question") int id);

    @GET("answers/getanswers.php/")
    Call<List<Answer>> getAnswers();


    @GET("answers/getanswers.php/")
    Call<List<Answer>> getAnswersById(@Query("id_answer") int id);
}
