package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.actions.FarmAction;

import java.io.IOException;

@WebServlet(name = "AnimalsController", value = "/animals")
public class AnimalsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        if (country != null) {
            FarmAction.getByCountry(request, country);
        } else {
            FarmAction.getAll(request);
        }

        request.getRequestDispatcher("WEB-INF/animals.jsp").forward(request, response);
    }
}
