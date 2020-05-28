package com.exhibitionCalendar.controller.commands;

import com.exhibitionCalendar.controller.commands.*;

public enum CommandList {
    REGISTER_USER_AND_CREDIT_CARD(new RegisterUserAndCreditCardCommand()),
    REGISTER_USER(new RegisterUserCommand()),
    REGISTER_CREDIT_CARD(new RegisterCreditCardCommand()),
    LOGIN(new LogInCommand()),
    LOGOUT(new LogOutCommand());

    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
