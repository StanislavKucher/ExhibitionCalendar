package com.exhibitionCalendar.service.implementations;

import com.exhibitionCalendar.model.dao.factory.DaoFactory;
import com.exhibitionCalendar.model.dao.interfaces.CreditCardDAO;
import com.exhibitionCalendar.model.entities.CreditCard;
import com.exhibitionCalendar.service.interfaces.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardServiceImpl implements CreditCardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardServiceImpl.class.getSimpleName());
    private CreditCardDAO<CreditCard, Integer> creditCardDAO = DaoFactory.getCreditCardDAO("MySQL");
}
