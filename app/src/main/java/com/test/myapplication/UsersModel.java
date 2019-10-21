package com.test.myapplication;

import com.google.gson.annotations.SerializedName;

public class UsersModel {
        private int ID;
        private String name;
        private String email;
        private String password;
        private String status;

        // Getter Methods

    @SerializedName("id")
        public int getID() {
            return ID;
        }
    @SerializedName("name")
        public String getName() {
            return name;
        }
    @SerializedName("email")
        public String getEmail() {
            return email;
        }

    @SerializedName("password")
        public String getPassword() {
            return password;
        }
    @SerializedName("status")
        public String getStatus() {
            return status;
        }

        // Setter Methods

        public void setID(int ID) {
            this.ID = ID;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
