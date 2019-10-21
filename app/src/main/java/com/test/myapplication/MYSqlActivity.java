package com.test.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MYSqlActivity extends AppCompatActivity {

    TextView txtUser;
    EditText name,email,password,status,iduser;
    Button btnadd;
    ApiHolder  apiHolder;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql);

        txtUser=findViewById(R.id.txtusers);
        iduser=findViewById(R.id.iduser);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        status=findViewById(R.id.status);
        btnadd=findViewById(R.id.btnadd);


        String url="http://85.132.63.190/myquiz/";
        String url2="http://20.20.20.24/myquiz/signup.php/";


        Gson gson=new GsonBuilder().serializeNulls().create();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        apiHolder=retrofit.create(ApiHolder.class);



        getUser();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (iduser.getText().toString()!=null ||iduser.getText().toString()!="")
                {
                    userUpdate();
                }
                else {
                    userSave();
                }*/

                userSave();
                getUser();
            }
        });
    }


    private void getUser() {

        Call<List<UsersModel>> call=apiHolder.getUsers();
        call.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {

                if(!response.isSuccessful())
                {
                    txtUser.setText(response.code());
                }

                List<UsersModel> users=response.body();
                for (UsersModel user:users)
                {
                    String content="";
                    content+="ID"+user.getID()+"\n";
                    content+="User ID "+user.getName()+"\n";
                    content+="Title "+user.getEmail()+"\n";
                    content+="Text: "+user.getPassword()+"\n\n";

                    txtUser.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable t) {

                Toast.makeText(context,"sehv bas verdi",Toast.LENGTH_LONG).show();

            }
        });

    }


    private void userSave(){
        Call<UsersModel> call=apiHolder.postUser(name.getText().toString(),email.getText().toString()
                , password.getText().toString(),status.getText().toString());
        call.enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {

            }
        });



    }


    private void userUpdate(){
        Call<UsersModel> call =apiHolder.updateUser(iduser.getText().toString(),name.getText().toString(),email.getText().toString()
                , password.getText().toString(),status.getText().toString());

        call.enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {

            }
        });
    }


    private void userDelete(){

    }




}
