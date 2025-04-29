package com.flashcardapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CardManager {

    public static List<Card> loadCards(String filePath, boolean invertCards) throws IOException {
        List<Card> cards = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            String[] parts = line.split(";", 2);
            if (parts.length == 2) {
                String question = parts[0].trim();
                String answer = parts[1].trim();
                if (invertCards) {
                    cards.add(new Card(answer, question));
                } else {
                    cards.add(new Card(question, answer));
                }
            }
        }

        return cards;
    }
}
