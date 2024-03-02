package com.example.cardsmicroservice.service.impl;

import com.example.cardsmicroservice.constants.CardsConstants;
import com.example.cardsmicroservice.dto.CardsDto;
import com.example.cardsmicroservice.entity.Cards;
import com.example.cardsmicroservice.exception.CardAlreadyExistsException;
import com.example.cardsmicroservice.exception.ResourceNotFoundException;
import com.example.cardsmicroservice.mapper.CardsMapper;
import com.example.cardsmicroservice.repository.CardRepository;
import com.example.cardsmicroservice.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;
    @Override
    public void createCard(String mobileNumber) {
           isCardRegistered(mobileNumber);
           cardRepository.save(createNewCard(mobileNumber));
    }
    public Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        newCard.setCardNumber(Long.toString(1_000_000_000L+new Random().nextInt(900000000)));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setMobileNumber(mobileNumber);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }


    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = findCard(mobileNumber);
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = findCardByCardNumber(cardsDto.getCardNumber());
        CardsMapper.mapToCards(cardsDto, cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = findCard(mobileNumber);
        cardRepository.deleteById(cards.getCardId());
        return true;
    }

    private void isCardRegistered(String mobileNumber) {
        Optional<Cards> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already has registered with the given mobileNumber :" + mobileNumber);
        }
    }
    private Cards findCard(String mobileNumber) {
       return cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
       );
    }
    private Cards findCardByCardNumber(String cardNumber) {
       return cardRepository.findByCardNumber(cardNumber).orElseThrow(
               () -> new ResourceNotFoundException("Card", "Card number", cardNumber)
       );

    }
}
