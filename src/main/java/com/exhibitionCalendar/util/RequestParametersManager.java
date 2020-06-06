package com.exhibitionCalendar.util;

import com.exhibitionCalendar.model.entities.CreditCard;
import com.exhibitionCalendar.model.entities.Role;
import com.exhibitionCalendar.model.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Pattern;

// Logging and javadoc will be done in the minor update commit
public class RequestParametersManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestParametersManager.class.getSimpleName());

    // EMAIL_PATTERN is taken from https://stackoverflow.com/questions/37853245/java-regex-issue-with-email-validation
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_PATTERN = "\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}";
    private static final String CARD_NUMBER_PATTERN = "\\d{16}|\\d{4}-\\d{4}-\\d{4}-\\d{4}";
    // here should be patterns for other fields

    public static Map<String, String> getParametersFromRequest(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        List<String> parameterNames = Collections.list(request.getParameterNames());

        for (String paramName : parameterNames) {
            parameters.put(paramName, request.getParameter(paramName).trim());
        }
        return parameters;
    }

    // if all fields transmitted from the form are valid -> returns empty string,
    // otherwise returns names of empty fields or messages which fields don't match pattern,
    // so they are incorrectly filled in
    public static String validateUser(Map<String, String> reqParams) {
        StringBuilder sb = new StringBuilder();

        if (!isLoaded(reqParams.get("name"))) {
            sb.append(" Firs Name");
        }
        if (!isLoaded(reqParams.get("surname"))) {
            sb.append(" Last name");
        }
        if (!isLoaded(reqParams.get("login"))) {
            sb.append(" Login");
        }
        if (!isLoaded(reqParams.get("email"))) {
            sb.append(" E-mail address");
        } else if (!Pattern.compile(EMAIL_PATTERN).matcher(reqParams.get("email")).matches()) {
            sb.append(" E-mail address is incorrect");
        }
        if (!isLoaded(reqParams.get("telephone"))) {
            sb.append(" Telephone number");
        } else if (!Pattern.compile(PHONE_PATTERN).matcher(reqParams.get("telephone")).matches()) {
            sb.append(" Telephone number is incorrect");
        }
        if (!isLoaded(reqParams.get("role")) || "Select account type".equals(reqParams.get("role"))) {
            sb.append(" Account type");
        }
        if (!isLoaded(reqParams.get("password"))) {
            sb.append(" Password");
        }
        if (!isLoaded(reqParams.get("repeat"))) {
            sb.append(" Repeat password");
        }

        return sb.toString();
    }

    // checks whether the field "Password" corresponds to the field "Repeat Password"
    public static String checkPasswordConfirmation(Map<String, String> reqParams) {
        String pass = reqParams.get("password");
        String rep = reqParams.get("repeat");
        return isLoaded(pass) && isLoaded(rep) ?
                pass.equals(rep) ? "" : " Field \"Repeat Password\" differs from Password"
                : " Either Password or Repeat Password is empty";

    }

    // if all fields transmitted from the form are valid -> returns empty string,
    // otherwise returns names of empty fields or messages which fields don't match pattern,
    // so they are incorrectly filled in
    public static String validateCreditCard(Map<String, String> reqParams) {
        StringBuilder sb = new StringBuilder();

        if (!isLoaded(reqParams.get("number"))) {
            sb.append(" Card Number");
        } else if (!Pattern.compile(CARD_NUMBER_PATTERN).matcher(reqParams.get("telephone")).matches()) {
            sb.append(" Card Number is incorrect. It should contain 16 digits");
        }
        if (!isLoaded(reqParams.get("month"))) {
            sb.append(" Month");
        }
        if (!isLoaded(reqParams.get("year"))) {
            sb.append(" Year");
        }
        if (!isLoaded(reqParams.get("cvv"))) {
            sb.append(" CVV");
        }

        return sb.toString();
    }

    // The method builds a user from request parameters after they've been checked, so reqParams must already be valid (checked by the validateUser method or a filter)
    public static User buildUserFromValidReqParams(Map<String, String> reqParams) {
        return new User.Builder()
                .setFirstName(reqParams.get("name"))
                .setLastName(reqParams.get("surname"))
                .setLogin(reqParams.get("login"))
                .setPassword(reqParams.get("password"))
                .setPhone(reqParams.get("telephone"))
                .setRole(Role.valueOf(reqParams.get("role").toUpperCase(Locale.ENGLISH)))
                .setEmail(reqParams.get("email"))
                .build();
    }

    // The method builds a credit card from request parameters after they've been checked, so reqParams must already be valid (checked by the validateCreditCard method or a filter)
    public static CreditCard buildCreditCardFromValidReqParams(Map<String, String> reqParams){
        return new CreditCard.Builder()
                .setNumber(reqParams.get("number"))
                .setCVV(Integer.parseInt(reqParams.get("cvv")))
                .setMonth(Integer.parseInt(reqParams.get("month")))
                .setYear(Integer.parseInt(reqParams.get("year")))
                // userID is auto incremented in DB while DB & UI aren't redone
                .build();
    }

    // TODO: Add to method's params CreditCard card till the DB & UI aren't redone for splitting, then again return to the current state
    public static void setSession(User user, CreditCard card, HttpSession session) {
        session.setAttribute("userId", user.getUserID());
        session.setAttribute("login", user.getLogin());
        session.setAttribute("name", user.getFirstName());
        session.setAttribute("surname", user.getLastName());
        session.setAttribute("role", user.getRole());
        session.setAttribute("number", card.getNumber());
    }

    private static boolean isLoaded(String str) {
        return !(str == null || str.isEmpty());
    }
}
