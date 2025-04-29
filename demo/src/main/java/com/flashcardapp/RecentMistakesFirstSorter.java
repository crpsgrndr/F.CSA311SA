package com.flashcardapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer {

    @Override
    public List<Card> organize(List<Card> cards) {
        List<Card> sorted = new ArrayList<>(cards);

        sorted.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                if (c1.getIncorrectCount() > 0 && c2.getIncorrectCount() == 0) {
                    return -1;
                } else if (c1.getIncorrectCount() == 0 && c2.getIncorrectCount() > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return sorted;
    }
}
