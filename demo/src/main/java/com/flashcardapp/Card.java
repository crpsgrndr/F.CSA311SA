package com.flashcardapp;

public class Card {
    private final String question;
    private final String answer;
    private int correctCount;
    private int incorrectCount;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.correctCount = 0;
        this.incorrectCount = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void markCorrect() {
        correctCount++;
    }

    public void markIncorrect() {
        incorrectCount++;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public double getAccuracy() {
        int totalAttempts = correctCount + incorrectCount;
        if (totalAttempts == 0) {
            return 0.0;
        }
        return (double) correctCount / totalAttempts;
    }
}
