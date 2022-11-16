package models.actions;

import jakarta.servlet.http.HttpServletRequest;
import models.managers.FarmManager;

public class FarmAction {

    public static final String FARMS_PARAM = "farms";

    public static void getAll(HttpServletRequest request){
        request.setAttribute(FARMS_PARAM, FarmManager.getAll());
    }

    public static void getByCountry(HttpServletRequest request, String country){
        request.setAttribute(FARMS_PARAM, FarmManager.getByCountry(country));
    }
}
