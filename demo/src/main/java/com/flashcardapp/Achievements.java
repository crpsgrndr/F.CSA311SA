package com.flashcardapp;

import java.util.List;

public class Achievements {

    public static void checkAchievements(List<Card> cards, double avgTime) {
        boolean allCorrect = true;
        boolean repeated = false;
        boolean confident = false;

        for (Card card : cards) {
            if (card.getIncorrectCount() > 0) {
                allCorrect = false;
            }
            if (card.getCorrectCount() >= 3) {
                confident = true;
            }
            if (card.getCorrectCount() + card.getIncorrectCount() >= 5) {
                repeated = true;
            }
        }

        System.out.println("\nAchievements:");
        if (allCorrect) {
            System.out.println("CORRECT: You answered all cards correctly in the last round!");
        }
        if (repeated) {
            System.out.println("REPEAT: You have answered a card 5 or more times!");
        }
        if (confident) {
            System.out.println("CONFIDENT: You have correctly answered a card at least 3 times!");
        }
        if (avgTime < 5.0) {
            System.out.println("GODSPEED: You answered with an average time under 5 seconds!");
        }
    }
}
