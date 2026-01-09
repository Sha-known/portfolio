package com.example.CodeCrafter;

public class User {
    private String username;
    private String gender;
    private String age;
    private String date_of_birth;
    private String mobile;
    private String email;
    private String password;

    public User(String username, String gender, String age, String date_of_birth, String mobile, String email, String password) {
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.date_of_birth = date_of_birth;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
