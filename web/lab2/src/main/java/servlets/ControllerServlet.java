package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import utils.ErrorUtil;

import static servlets.AreaCheckServlet.SC_INTERNAL_SERVER_ERROR;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Content-Type", "application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "*");
        try {
            if (request.getParameter("x") != null && request.getParameter("y") != null && request.getParameter("r") != null) {
                request.getRequestDispatcher("./check").forward(request, response);
            } else {
                request.getRequestDispatcher("./index.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            // Log the exception for debugging
            e.printStackTrace();
            ErrorUtil.sendError(response, SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    public static double getDouble(HttpServletRequest request, String parameter) {
        String param = request.getParameter(parameter);
        return Double.parseDouble(param.replace(",", "."));
    }
}
