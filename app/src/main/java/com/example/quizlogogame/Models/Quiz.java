package com.example.quizlogogame.Models;

public class Quiz {
    int s_img, e_img;
    String name;

    public Quiz(int s_img, int e_img, String name) {
        this.s_img = s_img;
        this.e_img = e_img;
        this.name = name;
    }

    public int getS_img() {
        return s_img;
    }

    public void setS_img(int s_img) {
        this.s_img = s_img;
    }

    public int getE_img() {
        return e_img;
    }

    public void setE_img(int e_img) {
        this.e_img = e_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
