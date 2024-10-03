package utils;  

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

public class ErrorUtil {

    public static void sendError(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        var json = new Gson();
        Map<String, Object> jsonResponse = new HashMap<>() {{
            put("error", errorMessage);
            put("status", "Error");
        }};

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toJson(jsonResponse));
        response.setStatus(statusCode);
    }
}

