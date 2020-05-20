package com.exhibitionCalendar.model.dao.interfaces;

public interface CreditCardDAO<CreditCard, Integer> extends BaseDAO<CreditCard, Integer> {

    CreditCard retrieveByUserId(Integer userId);

}
