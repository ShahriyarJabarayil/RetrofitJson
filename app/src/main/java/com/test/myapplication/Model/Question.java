package com.test.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class Question {
    private String id_question;
    private String id_category;
    private String text;
    private String id_answer;
    private String time;
    private String note;
    private String id_type;
    private String photo_path;


    // Getter Methods

    @SerializedName("id_question")
    public String getId_question() {
        return id_question;
    }

    @SerializedName("cat_category")
    public String getId_category() {
        return id_category;
    }

    @SerializedName("text")
    public String getText() {
        return text;
    }

    @SerializedName("id_answer")
    public String getId_answer() {
        return id_answer;
    }


    @SerializedName("time")
    public String getTime() {
        return time;
    }
    @SerializedName("note")
    public String getNote() {
        return note;
    }


    @SerializedName("id_type")
    public String getId_type() {
        return id_type;
    }


    @SerializedName("photo_path")
    public String getPhoto_path() {
        return photo_path;
    }

    // Setter Methods

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId_answer(String id_answer) {
        this.id_answer = id_answer;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }
}