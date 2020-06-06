package com.exhibitionCalendar.service.interfaces;

import com.exhibitionCalendar.model.entities.CreditCard;

import java.util.Map;

public interface CreditCardService {
    CreditCard createNewCreditCard(Map<String, String> reqParams);
}
