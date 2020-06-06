package com.exhibitionCalendar.controller.commands;

import com.exhibitionCalendar.model.entities.CreditCard;
import com.exhibitionCalendar.model.entities.User;
import com.exhibitionCalendar.service.implementations.CreditCardServiceImpl;
import com.exhibitionCalendar.service.implementations.UserServiceImpl;
import com.exhibitionCalendar.service.interfaces.CreditCardService;
import com.exhibitionCalendar.service.interfaces.UserService;
import com.exhibitionCalendar.util.RequestParametersManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// Will be split up into separate RegisterUser & RegisterCreditCard Commands respectively in the future
// Will be logged in the nearest future
// Request parameters' validation might be transmitted to a Filter in some distant future
public class RegisterUserAndCreditCardCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserAndCreditCardCommand.class.getSimpleName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Method execute in RegisterUserAndCreditCardCommand starts");

        Map<String, String> requestParameters = RequestParametersManager.getParametersFromRequest(request);

        // Request parameters' validation
        String userValidation = RequestParametersManager.validateUser(requestParameters);
        if (userValidation.length() != 0) {
            request.setAttribute("err", userValidation);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String passConfirmation = RequestParametersManager.checkPasswordConfirmation(requestParameters);
        if (passConfirmation.length() != 0) {
            request.setAttribute("err", passConfirmation);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String cardValidation = RequestParametersManager.validateCreditCard(requestParameters);
        if (cardValidation.length() != 0) {
            request.setAttribute("err", cardValidation);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // The end of request parameters' validation

        UserService userService = new UserServiceImpl();
        User user = userService.createNewUser(requestParameters);

        CreditCardService cardService = new CreditCardServiceImpl();
        CreditCard creditCard = cardService.createNewCreditCard(requestParameters);

        if (user == null || creditCard == null) {
            request.setAttribute("message", "The user or credit card already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        request.setAttribute("message", "You've been successfully registered");
        RequestParametersManager.setSession(user, creditCard, request.getSession(true));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

}
