package com.flashcardapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorstFirstSorter implements CardOrganizer {

    @Override
    public List<Card> organize(List<Card> cards) {
        List<Card> sorted = new ArrayList<>(cards);

        sorted.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return Integer.compare(c2.getIncorrectCount(), c1.getIncorrectCount());
            }
        });

        return sorted;
    }
}
