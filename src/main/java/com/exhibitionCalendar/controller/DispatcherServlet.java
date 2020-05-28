package com.exhibitionCalendar.controller;

import com.exhibitionCalendar.controller.commands.Command;
import com.exhibitionCalendar.controller.commands.CommandList;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Controller
@WebServlet("/DispatcherServlet") // "/ExhibitionCalendar"
public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Method doGet starts");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Method doPost starts");
        processRequest(request, response);
    }

    // Sets and executes command at controller's level
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String commandName = request.getParameter("command");
            Command command = CommandList.valueOf(commandName).getCommand();
            command.execute(request, response);
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Command parameter was wrong or null");
            LOGGER.debug(e);
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
