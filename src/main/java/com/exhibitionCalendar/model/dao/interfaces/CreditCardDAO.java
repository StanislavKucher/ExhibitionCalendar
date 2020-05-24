package com.exhibitionCalendar.model.dao.interfaces;

public interface CreditCardDAO<CreditCard, Integer> extends BaseDAO<CreditCard, Integer> {

    CreditCard retrieveByUserId(Integer userId);

    // might be refactored to BigDecimal return type
    double getBalanceByCardId(Integer creditCardId);

    // method parameter newBalance might be refactored to BigDecimal type
    boolean updateBalance(Integer creditCardId, double newBalance);

}
