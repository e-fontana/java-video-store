package com.videostore.store;

import com.videostore.costumer.Costumer;
import com.videostore.models.Film;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Store {
    ArrayList<Film> availableFilms = new ArrayList<>(
        Arrays.asList(
            new Film("Titanic", 12.9, 5),
            new Film("The Godfather", 9.9, 5),
            new Film("The Shawshank Redemption", 7.9, 5),
            new Film("The Dark Knight", 8.9, 5),
            new Film("The Lord of the Rings: The Return of the King", 11.9, 5),
            new Film("Pulp Fiction", 6.9, 5),
            new Film("Forrest Gump", 5.9, 5),
            new Film("The Matrix", 7.9, 5),
            new Film("Harry Potter: Chamber of Secrets", 10.9, 5)
        )
    );

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = 0;
            System.out.println("\n--- Welcome to the Video Store ---");
            System.out.println("What is your name?");
            String name = scanner.nextLine();
            Costumer costumer = new Costumer(name);
            Store store = new Store();

            
            while (choice != 3) {
                clearScreen();
                System.out.println("\nHello, " + costumer.getName() + "!\n");
                System.out.println("1. Rent a film");
                System.out.println("2. View rented films");
                System.out.println("3. Exit");
                System.out.print("\nEnter your choice: ");
                choice = scanner.nextInt();

                
                switch (choice) {
                    case 1 -> {
                        clearScreen();
                        Locale brLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(brLocale);
                        ArrayList<Film> availableFilms = store.getAvailableFilms();
                        System.out.println("\n--- Rent a film ---");
                        for (int i = 0; i < availableFilms.size(); i++) {
                            System.out.println((i + 1) + ". " + availableFilms.get(i).getTitle() + " - " + currencyFormat.format(availableFilms.get(i).getPrice()) + " - Available: " + availableFilms.get(i).getAvailable());
                        }
                        System.out.print("Enter the number of the film you want to rent: ");
                        int filmNumber = scanner.nextInt();
                        if (filmNumber < 1 ) {
                            System.out.println("Invalid film number");
                            break;
                        } else if (filmNumber > availableFilms.size()) {
                            break;
                        }
                        Film film = availableFilms.get(filmNumber - 1);
                        costumer.rentFilm(film);
                        film.setAvailable(film.getAvailable() - 1);
                        System.out.println("\nCongrats! You have rented: " + film.getTitle());
                    }
                    case 2 -> {
                        clearScreen();
                        System.out.println("\n--- View rented films ---");
                        for (int i = 0; i < costumer.getRents().size(); i++) {
                            System.out.println(costumer.getRents().get(i).getRentInfo());
                        }
                        while (true) { 
                            System.out.print("\nPress 1 to return to the main menu: ");
                            int back = scanner.nextInt();
                            if (back == 1) {
                                break;
                            }
                        }
                    }
                    default -> throw new AssertionError();
                }
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public ArrayList<Film> getAvailableFilms() {
        return availableFilms;
    }
}
