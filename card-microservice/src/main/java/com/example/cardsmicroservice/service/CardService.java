package com.example.cardsmicroservice.service;

import com.example.cardsmicroservice.dto.CardsDto;

public interface CardService {
    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
