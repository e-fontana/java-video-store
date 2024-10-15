package com.videostore.costumer;

import com.videostore.models.Film;
import com.videostore.rent.Rent;
import java.util.ArrayList;

public class Costumer {
    String name;
    ArrayList<Rent> rents = new ArrayList<>();
    Double credit = 0.0;

    public Costumer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public ArrayList<Rent> getRents(boolean onlyNotReturned) {
        if (onlyNotReturned) {
            ArrayList<Rent> notReturnedRents = new ArrayList<>();
            for (Rent rent : rents) {
                if (!rent.isReturned()) {
                    notReturnedRents.add(rent);
                }
            }
            return notReturnedRents;
        }
        return rents;
    }

    public Rent rentFilm(Film film) {
        Rent rent = new Rent(film);
        rents.add(rent);
        return rent;
    }

    public Rent returnRent(String id) {
        for (Rent rent : rents) {
            if (rent.getRentInfo().contains(id)) {
                rent.returnFilm();
                return rent;
            }
        }
        return null;
    }
}
