package com.exhibitionCalendar.service.implementations;

import com.exhibitionCalendar.model.dao.factory.DaoFactory;
import com.exhibitionCalendar.model.dao.interfaces.CreditCardDAO;
import com.exhibitionCalendar.model.entities.CreditCard;
import com.exhibitionCalendar.service.interfaces.CreditCardService;
import org.apache.log4j.Logger;

public class CreditCardServiceImpl implements CreditCardService {
    private static final Logger LOGGER = Logger.getLogger(CreditCardServiceImpl.class.getSimpleName());
    private CreditCardDAO<CreditCard, Integer> creditCardDAO = DaoFactory.getCreditCardDAO("MySQL");
}
