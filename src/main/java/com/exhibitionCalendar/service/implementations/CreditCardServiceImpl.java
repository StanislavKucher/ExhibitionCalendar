package com.exhibitionCalendar.service.implementations;

import com.exhibitionCalendar.model.dao.factory.DaoFactory;
import com.exhibitionCalendar.model.dao.interfaces.CreditCardDAO;
import com.exhibitionCalendar.model.entities.CreditCard;
import com.exhibitionCalendar.service.interfaces.CreditCardService;
import com.exhibitionCalendar.util.RequestParametersManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CreditCardServiceImpl implements CreditCardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardServiceImpl.class.getSimpleName());
    private CreditCardDAO<CreditCard, Integer> creditCardDAO = DaoFactory.getCreditCardDAO("MySQL");

    @Override
    public CreditCard createNewCreditCard(Map<String, String> reqParams) {
        LOGGER.info("Method createNewCreditCard starts with the following request params {}", reqParams);

        if (creditCardDAO.retrieveByCardNumber(reqParams.get("number")) != null) { //
            LOGGER.info("Such creditCard already exists");
            return null;
        }
        LOGGER.info("It's a new creditCard");

        CreditCard card = RequestParametersManager.buildCreditCardFromValidReqParams(reqParams);

        creditCardDAO.create(card);

        LOGGER.debug("The method createNewCreditCard returns a new creditCard {}", card);
        return card;
    }
}
