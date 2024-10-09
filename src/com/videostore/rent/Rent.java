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

    public Rent(Film film) {
        this.id = UUID.randomUUID().toString();
        this.film = film;
        this.rentDate = LocalDate.now();
        this.returnDate = this.rentDate.plusDays(7);
    }

    public String  getRentInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
            "\n\nID: %s\nFilm: %s\nRent date: %s\nReturn date: %s",
             id,
             film.getTitle(),
             rentDate.format(formatter),
             returnDate.format(formatter)
            );
    }
}
