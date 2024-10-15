package com.videostore.rent;


import com.videostore.models.Film;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Rent {
    String id;
    Film film;
    LocalDate rentDate;
    LocalDate returnDate;
    Boolean returned = false;

    public Rent(Film film) {
        this.id = UUID.randomUUID().toString();
        this.film = film;
        this.rentDate = LocalDate.now();
        this.returnDate = this.rentDate.plusDays(7);
    }

    public Boolean isReturned() {
        return returned;
    }

    public String getId() {
        return id;
    }

    public Film getFilm() {
        return film;
    }

    public String getRentInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
            "\nID: %s\nFilm: %s\nRent date: %s\nReturn date: %s",
             id,
             film.getTitle(),
             rentDate.format(formatter),
             returnDate.format(formatter)
        );
    }

    public void returnFilm() {
        this.returned = true;
    }
}
