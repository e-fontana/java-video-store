package com.videostore.costumer;

import com.videostore.models.Film;
import com.videostore.rent.Rent;
import java.util.ArrayList;

public class Costumer {
    String name;
    ArrayList<Rent> rents = new ArrayList<>();

    public Costumer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Rent> getRents() {
        return rents;
    }

    public Rent rentFilm(Film film) {
        Rent rent = new Rent(film);
        rents.add(rent);
        return rent;
    }
}
