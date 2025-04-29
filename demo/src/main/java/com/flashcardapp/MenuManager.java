package com.flashcardapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MenuManager {

    public static void printMenu() {
        System.out.println("\nFlashcard App - Menu:");
        System.out.println("1. Study cards");
        System.out.println("2. Add new card");
        System.out.println("3. View recent mistakes");
        System.out.println("4. Change card order");
        System.out.println("5. View achievements");
        System.out.println("6. Help");
        System.out.println("7. Exit");
    }

    public static void addNewCard(Scanner scanner, List<Card> cards) {
        System.out.print("Enter new question: ");
        String question = scanner.nextLine().trim();
        System.out.print("Enter answer: ");
        String answer = scanner.nextLine().trim();
        cards.add(new Card(question, answer));
        System.out.println("New card added successfully!");
    }

    public static void viewRecentMistakes(List<Card> cards) {
        System.out.println("\nRecent Mistakes:");
        for (Card card : cards) {
            if (card.getIncorrectCount() > 0) {
                System.out.println("- " + card.getQuestion() + " (Mistakes: " + card.getIncorrectCount() + ")");
            }
        }
    }

    public static CardOrganizer changeCardOrder(Scanner scanner) {
        System.out.println("Choose new order:");
        System.out.println("1. Random");
        System.out.println("2. Worst First");
        System.out.println("3. Recent Mistakes First");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                return cardsList -> {
                    List<Card> copy = new ArrayList<>(cardsList);
                    Collections.shuffle(copy);
                    return copy;
                };
            case "2":
                return new WorstFirstSorter();
            case "3":
                return new RecentMistakesFirstSorter();
            default:
                System.out.println("Invalid choice. Keeping previous order.");
                return cardsList -> {
                    List<Card> copy = new ArrayList<>(cardsList);
                    Collections.shuffle(copy);
                    return copy;
                };
        }
    }
}
