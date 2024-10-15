package com.videostore.store;

import com.videostore.costumer.Costumer;
import com.videostore.models.Film;
import com.videostore.rent.Rent;
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
            
            Locale brLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(brLocale);

            while (choice != 5) {
                clearScreen();
                System.out.println("\nHello, " + costumer.getName() + "!\n");
                System.out.println("1. Rent a film");
                System.out.println("2. View rented films");
                System.out.println("3. Insert credits");
                System.out.println("4. Return rented film");
                System.out.println("5. Exit");
                System.out.print("\nEnter your choice: ");
                choice = scanner.nextInt();

                
                switch (choice) {
                    case 1 -> {
                        clearScreen();
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
                        if (film.getPrice() <= costumer.getCredit()) {
                            costumer.setCredit(costumer.getCredit() - film.getPrice());
                            costumer.rentFilm(film);
                            film.setAvailable(film.getAvailable() - 1);
                            System.out.println("\nCongrats! You have rented: " + film.getTitle());
                        } else {
                            clearScreen();
                            while (true) {
                                System.out.print("You don't have enough credits to rent this film. Press 1 to return to the main menu: ");
                                int back = scanner.nextInt();
                                if (back == 1) {
                                    break;
                                }
                            }
                            System.out.println("You don't have enough credits to rent this film");
                            break;
                        }
                    }
                    case 2 -> {
                        clearScreen();
                        System.out.println("\n--- View rented films ---");
                        for (int i = 0; i < costumer.getRents(false).size(); i++) {
                            System.out.println(costumer.getRents(false).get(i).getRentInfo());
                        }
                        while (true) { 
                            System.out.print("\nPress 1 to return to the main menu: ");
                            int back = scanner.nextInt();
                            if (back == 1) {
                                break;
                            }
                        }
                    }
                    case 3 -> {
                        clearScreen();
                        System.out.println("\n--- Insert credits ---");
                        System.out.print("Current credits: " + currencyFormat.format(costumer.getCredit()) + "\n");
                        System.out.print("Enter the amount you want to insert: ");
                        double credit = scanner.nextDouble();
                        costumer.setCredit(costumer.getCredit() + credit);
                    }
                    case 4 -> {
                        clearScreen();
                        System.out.println("\n--- Return rented film ---");
                        ArrayList<Rent> rents = costumer.getRents(true);
                        if (rents.isEmpty()) {
                            System.out.println("You don't have any rented films");
                            while (true) { 
                                System.out.print("\nPress 1 to return to the main menu: ");
                                int back = scanner.nextInt();
                                if (back == 1) {
                                    break;
                                }
                            }
                            break;
                        }
                        for (int i = 0; i < rents.size(); i++) {
                            System.out.println((i + 1) + ". " + rents.get(i).getRentInfo());
                        }
                        System.out.print("\nEnter the number of the rent you want to return: ");
                        int rentIndex = scanner.nextInt() - 1;
                        if (rentIndex < 0 || rentIndex >= rents.size()) {
                            System.out.println("Invalid film number");
                            break;
                        }
                        Film film = rents.get(rentIndex).getFilm();
                        
                        costumer.returnRent(rents.get(rentIndex).getId());
                        film.setAvailable(film.getAvailable() + 1);
                        System.out.println("Film returned successfully");

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
