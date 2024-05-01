package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String isbn;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Transient
    private Integer numberOfPages;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String toString() {
        return " isbn: " + isbn + " , title: " + title;
    }
}
