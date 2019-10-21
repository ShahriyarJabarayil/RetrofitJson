package com.test.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.myapplication.HolderClass.ApiQuestions;
import com.test.myapplication.Model.Question;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {
    TextView txtTime,txtQuestion;
    Button btnA,btnB,btnC,btnD;
    ApiQuestions apiQuestions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        txtTime=findViewById(R.id.txttime);
        txtQuestion=findViewById(R.id.txtquestion);

        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);

        String url="http://20.20.20.24/myquiz/";

        Gson gson=new GsonBuilder().serializeNulls().create();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        apiQuestions=retrofit.create(ApiQuestions.class);


        getQuestion();
        getAllQuestion();
    }

    private void getAllQuestion() {
        Call<List<Question>> call =apiQuestions.getAllQuestions();
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful())
                {
                    txtQuestion.setText(response.code());
                }

                List<Question> users=response.body();
                for (Question user:users)
                {
                    String content="";
                    content+="ID"+user.getTime()+"\n";
                    content+="User ID "+user.getText()+"\n\n";
                    txtQuestion.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });
    }

    private void getQuestion() {
        Call<Question> call =apiQuestions.getQuestion(5);

        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {

                try {
                    if (response.isSuccessful()) {
                        Question question = response.body();
                        txtTime.setText(question.getTime());
                        txtQuestion.setText(question.getText());
                    }
                }
                catch (Exception e)
                {
                    throw  e;
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {

            }
        });
    }
}
