package com.example.practice.Helpers;

public class BookHelper {
    int image;
    String title,author;

    public BookHelper(int image, String title, String author) {
        this.image = image;
        this.title = title;
        this.author = author;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
