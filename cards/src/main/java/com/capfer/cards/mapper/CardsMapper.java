package com.capfer.cards.mapper;

import com.capfer.cards.dto.CardDTO;
import com.capfer.cards.entity.Card;

public final class CardsMapper {

    public static Card mapToEntity(CardDTO cardDTO) {
        Card card = new Card();
        card.setMobileNumber(cardDTO.getMobileNumber());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setTotalLimit(cardDTO.getTotalLimit());
        card.setAvailableAmount(cardDTO.getAvailableAmount());
        card.setAmountUsed(cardDTO.getAmountUsed());
        return card;
    }

    public static Card mapToChangedEntity(Card card, CardDTO cardDTO) {
        card.setMobileNumber(cardDTO.getMobileNumber());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setTotalLimit(cardDTO.getTotalLimit());
        card.setAvailableAmount(cardDTO.getAvailableAmount());
        card.setAmountUsed(cardDTO.getAmountUsed());
        return card;
    }

    public static CardDTO mapToDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setMobileNumber(card.getMobileNumber());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setTotalLimit(card.getTotalLimit());
        cardDTO.setAvailableAmount(card.getAvailableAmount());
        cardDTO.setAmountUsed(cardDTO.getAmountUsed());
        return cardDTO;
    }
}
