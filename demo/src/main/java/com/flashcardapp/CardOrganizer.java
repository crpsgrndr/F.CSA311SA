package com.flashcardapp;

import java.util.List;

public interface CardOrganizer {
    /**
     * Return the list of cards in the order this organizer defines.
     * @param cards List of all cards
     * @return Ordered list of cards
     */
    List<Card> organize(List<Card> cards);
}
