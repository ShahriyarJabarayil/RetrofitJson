package com.test.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.myapplication.HolderClass.ApiJsonQuiz;
import com.test.myapplication.Model.Answer;
import com.test.myapplication.Model.Question;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {
    TextView txtTime,txtQuestion,txtQuestNo;
    Button btnA,btnB,btnC,btnD;
    ImageView imgPrevious,imgNext;

    ApiJsonQuiz apiJsonQuiz;
    Question question;
    Context context=this;
    CountDownTimer countDownTimer;
    long timeLeftInMillies;//=30000;
    int count=0;



    List<Answer> answerList;
    List<Question> listquestions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        txtTime=findViewById(R.id.txttime);
        txtQuestion=findViewById(R.id.txtquestion);
        txtQuestNo=findViewById(R.id.txtquest_no);

        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);

        imgPrevious=findViewById(R.id.imgprevious);
        imgNext=findViewById(R.id.imgnext);


        String url="http://20.20.20.24/myquiz/";

        Gson gson=new GsonBuilder().serializeNulls().create();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        apiJsonQuiz =retrofit.create(ApiJsonQuiz.class);





        getAllQuestions();
        getAnswers();
        //findquestion(count);
        //questionShow(count);


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllQuestions();
                txtQuestion.setText(listquestions.get(1).getText());
            }
        });


        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestion1();
            }
        });


        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>0) {
                    count--;
                    findquestion(count);
                    //questionShow(count);
                }
                else
                    return;
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count<listquestions.size()-1) {
                    count++;
                    //questionShow(count);
                    findquestion(count);
                }
                else
                    return;
            }
        });

    }

    private void getAllQuestions() {
        Call<List<Question>> call = apiJsonQuiz.getAllQuestions();
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful())
                {
                    txtQuestion.setText(response.code());
                }

                List<Question> questions=response.body();
                for (Question question:questions)
                {
                    String content="";
                    content+="ID"+question.getTime()+"\n";
                    content+="User ID "+question.getText()+"\n\n";
                    //txtQuestion.append(content);

                }
                listquestions=questions;
               // findquestion(1);

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });
    }

    private void getAnswers(){

        Call<List<Answer>> call= apiJsonQuiz.getAnswers();

        call.enqueue(new Callback<List<Answer>>() {
            @Override
            public void onResponse(Call<List<Answer>> call, Response<List<Answer>> response) {

                if(!response.isSuccessful()) {

                    Toast.makeText(context, "00000", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    List<Answer> answers = response.body();
                    for (Answer answer : answers) {


                        String content="";
                        content+="ID"+answer.getChoiceB();
                        content+="User ID "+answer.getChoiceB();
                        txtQuestion.append(content);
                    }

                    answerList=answers;
                    Toast.makeText(context, "111111", Toast.LENGTH_LONG).show();
                }
                //questionShow(count);
                findquestion(0);
            }

            @Override
            public void onFailure(Call<List<Answer>> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();

            }
        });




    }


    private void questionShow(int k)
    {


        boolean checkquestList=false;
        boolean checkanswList=false;
        if (listquestions!=null)
        {
            checkquestList=true;
            txtTime.setText(listquestions.size());
        }
        else {
            getAllQuestions();
        }


        if (answerList!=null)
        {
            checkanswList=true;
        }
        else {
            getAnswers();
        }

        if (checkanswList && checkquestList)
        {
            txtQuestion.setText(listquestions.get(k).getText());

            txtTime.setText(listquestions.get(k).getTime());
            timeLeftInMillies= Integer.parseInt(listquestions.get(k).getTime());
            timeLeftInMillies=timeLeftInMillies*1000;
        }

    }






    private void getQuestion1() {
     Call<List<Question>> call = apiJsonQuiz.getQuestion1(5);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {

                String content = "";
                if (response.body() != null) {

                    Toast.makeText(TestActivity.this, "ugurludur", Toast.LENGTH_SHORT).show();
                    List<Question> questions = response.body();
                    String b = "";
                    for (Question question : questions) {

                        content += "ID" + question.getTime() + "\n";
                        b += "User ID " + question.getText() + "\n\n";

                    }

                    txtQuestion.setText(b);
                    // txtQuestion.append(content);
                }
                else
                    Toast.makeText(TestActivity.this, "Xeta bas verdi responso bos geldi", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {


                Toast.makeText(TestActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getAnswer(int t) {


        Call<List<Answer>> call = apiJsonQuiz.getAnswersById(t);
        call.enqueue(new Callback<List<Answer>>() {
            @Override
            public void onResponse(Call<List<Answer>> call, Response<List<Answer>> response) {
                if (response.isSuccessful()) {
                    List<Answer> answers=response.body();
                    String a="";
                    String b="";
                    String c="";
                    String d="";
                    for (Answer answer:answers)
                    {
                       a=answer.getChoiceA();
                       b=answer.getChoiceB();
                       c=answer.getChoiceC();
                       d=answer.getChoiceD();

                    }

                    btnA.setText(a);
                    btnB.setText(b);
                    btnC.setText(c);
                    btnD.setText(d);

                } else
                    Toast.makeText(context, "ugursuzdu", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Answer>> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void findquestion(int q)
    {
        if (listquestions!=null ) {

            txtQuestion.setText(listquestions.get(q).getText());

            txtTime.setText(listquestions.get(q).getTime());
            timeLeftInMillies = Integer.parseInt(listquestions.get(q).getTime());
            timeLeftInMillies = timeLeftInMillies * 1000;

            startCountDownTime();

            //yeni
            String id_ans = listquestions.get(q).getId_answer();

            String a = "asa";
            String n = String.valueOf(count + 1);
            String N = String.valueOf(listquestions.size());

            txtQuestNo.setText(n + " / " + N);
            int k=0;

            for (int i = 0; i < answerList.size(); i++) {
                if (answerList.get(i).getId_answer() == id_ans) {
                    k=i;
                    a = answerList.get(i).getChoiceA();
                    btnC.setText(answerList.get(i).getChoiceC());
                }
               // Toast.makeText(context, "00000" + a, Toast.LENGTH_LONG).show();
                btnA.setText(answerList.get(k).getChoiceA());
                btnB.setText(id_ans + answerList.size());


            }

        }
        else
        {
            getAllQuestions();
           // getAnswers();

          //  findquestion(1);
        }
            //Toast.makeText(context,"Sual tapilmadi",Toast.LENGTH_LONG).show();
    }







    private void startCountDownTime()
    {
        countDownTimer=new CountDownTimer(timeLeftInMillies,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillies=millisUntilFinished;
                updateCountDownTime();

            }

            @Override
            public void onFinish() {

                timeLeftInMillies=0;
                updateCountDownTime();
            }
        }.start();
    }

    private void updateCountDownTime()
    {
        int minut=(int) (timeLeftInMillies/1000)/60;
        int second=(int) (timeLeftInMillies/1000)%60;

        String formatTime=String.format(Locale.getDefault(),"%02d:%02d",minut,second);
        txtTime.setText(formatTime);

        if(timeLeftInMillies<10000)
        {
            txtTime.setTextColor(Color.RED);
        }

        else
            txtTime.setTextColor(Color.BLACK);
    }


}
