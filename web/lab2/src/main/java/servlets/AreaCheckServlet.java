package servlets;

import beans.ResultsBean;
import com.google.gson.Gson;
import utils.AreaChecker;
import utils.ErrorUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    public static final int SC_UNPROCESSABLE_ENTITY = 422;
    public static final int SC_INTERNAL_SERVER_ERROR = 500;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDateTime time = LocalDateTime.now();
        double x = 0;
        double y = 0;
        double r = 0;
        try {
            x = ControllerServlet.getDouble(request, "x");
            y = ControllerServlet.getDouble(request, "y");
            r = ControllerServlet.getDouble(request, "r");

            ResultsBean bean = (ResultsBean) request.getSession().getAttribute("results");
            if (bean == null) {
                bean = new ResultsBean();
                request.getSession().setAttribute("results", bean);
            }

            ResultsBean.Result result = new ResultsBean.Result(String.valueOf(x),
                    String.valueOf(y), String.valueOf(r), AreaChecker.isInArea(x, y, r));
            bean.addResult(result);

            // code for checking script evaluation time
            Duration duration = Duration.between(time, LocalDateTime.now());
            System.out.println("Time elapsed: " + duration + " milliseconds");

            // Respond with JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(result));
        } catch (NumberFormatException e) {
            ErrorUtil.sendError(response, SC_UNPROCESSABLE_ENTITY, "Invalid number format");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorUtil.sendError(response, SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}
