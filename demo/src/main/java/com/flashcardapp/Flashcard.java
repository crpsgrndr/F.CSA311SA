package com.flashcardapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Flashcard {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            printHelp();
            return;
        }

        if (Arrays.asList(args).contains("--help")) {
            printHelp();
            return;
        }

        String cardsFile = args[0];
        boolean invertCards = false;
        String order = "random";
        int repetitions = 1;

        for (int i = 1; i < args.length; i++) {
            String option = args[i];
            switch (option) {
                case "--order":
                    if (i + 1 < args.length) {
                        order = args[++i];
                        if (!Arrays.asList("random", "worst-first", "recent-mistakes-first").contains(order)) {
                            System.out.println("Error: Unknown order: " + order);
                            return;
                        }
                    } else {
                        System.out.println("Error: Missing value for --order");
                        return;
                    }
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) {
                        try {
                            repetitions = Integer.parseInt(args[++i]);
                            if (repetitions <= 0) {
                                System.out.println("Error: repetitions must be positive.");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid number for repetitions.");
                            return;
                        }
                    } else {
                        System.out.println("Error: Missing value for --repetitions");
                        return;
                    }
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    System.out.println("Error: Unknown option: " + option);
                    return;
            }
        }

        // Load cards
        List<Card> cards;
        try {
            cards = CardManager.loadCards(cardsFile, invertCards);
        } catch (IOException e) {
            System.out.println("Error reading file: " + cardsFile);
            return;
        }

        CardOrganizer organizer = getOrganizer(order);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            MenuManager.printMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    long sessionStart = System.currentTimeMillis();
                    StudySession.start(scanner, cards, organizer, repetitions);
                    long sessionEnd = System.currentTimeMillis();
                    double avgTime = (sessionEnd - sessionStart) / 1000.0 / (cards.size() * repetitions);
                    Achievements.checkAchievements(cards, avgTime);
                    break;
                case "2":
                    MenuManager.addNewCard(scanner, cards);
                    break;
                case "3":
                    MenuManager.viewRecentMistakes(cards);
                    break;
                case "4":
                    organizer = MenuManager.changeCardOrder(scanner);
                    break;
                case "5":
                    Achievements.checkAchievements(cards, 9999.0);
                    break;
                case "6":
                    printHelp();
                    break;
                case "7":
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static CardOrganizer getOrganizer(String order) {
        switch (order) {
            case "recent-mistakes-first":
                return new RecentMistakesFirstSorter();
            case "worst-first":
                return new WorstFirstSorter();
            default:
                return cardsList -> {
                    List<Card> copy = new ArrayList<>(cardsList);
                    Collections.shuffle(copy);
                    return copy;
                };
        }
    }

    private static void printHelp() {
        System.out.println("\nFlashcard Usage:");
        System.out.println("flashcard <cards-file> [options]");
        System.out.println("Options:");
        System.out.println("  --help               Show this help message and exit.");
        System.out.println("  --order <order>      Order type: random, worst-first, recent-mistakes-first (default: random)");
        System.out.println("  --repetitions <num>  Require answering each card correctly this many times (default: 1)");
        System.out.println("  --invertCards        Swap question and answer (default: false)");
    }
}
