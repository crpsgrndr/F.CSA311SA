package com.flashcardapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudySession {

    public static void start(Scanner scanner, List<Card> cards, CardOrganizer organizer, int repetitions) {
        int totalQuestions = cards.size() * repetitions;
        int correctAnswers = 0;
        long startTime = System.currentTimeMillis();

        for (int r = 0; r < repetitions; r++) {
            List<Card> orderedCards = new ArrayList<>(organizer.organize(cards));

            for (Card card : orderedCards) {
                System.out.println("Question: " + card.getQuestion());
                String userAnswer = scanner.nextLine().trim();
                if (userAnswer.equalsIgnoreCase(card.getAnswer())) {
                    System.out.println("Correct!");
                    card.markCorrect();
                    correctAnswers++;
                } else {
                    System.out.println("Wrong! Correct answer: " + card.getAnswer());
                    card.markIncorrect();
                }
            }
        }

        long endTime = System.currentTimeMillis();
        double avgTimePerAnswer = (endTime - startTime) / 1000.0 / totalQuestions;
        System.out.printf("Session finished. Correct answers: %d/%d. Average time per answer: %.2f seconds.%n",
                correctAnswers, totalQuestions, avgTimePerAnswer);
    }
}
