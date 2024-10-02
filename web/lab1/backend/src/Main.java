import com.fastcgi.FCGIInterface;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Main {
    private static final String RESPONSE_TEMPLATE = "HTTP/1.1 200 OK\n" +
            "Content-Type: application/json\n" +
            "Access-Control-Allow-Origin: *\n" +
            "Access-Control-Allow-Methods: POST\n" +
            "Access-Control-Allow-Headers: *\n" +
            "Access-Control-Allow-Credentials: true\n" +
            "Content-Length: %d\n\n%s";

    public static void main (String args[]) {
        var validator = new Validator();
        var fcgiInterface = new FCGIInterface();
        while(fcgiInterface.FCGIaccept() >= 0) {
            Instant executionStart = Instant.now();

            try {
                var request = new Request().fillBody(FCGIInterface.request.params);
                var requestBody = request.getBody();
                int x = Integer.parseInt(requestBody.get("x").toString());
                float y = Float.parseFloat(requestBody.get("y").toString());
                int r = Integer.parseInt(requestBody.get("r").toString());

                if (validator.validate(x, y, r)) {
                    sendJson(String.format("{\"result\": %b, \"execution_time_ns\": %d, \"current_time\": \"%s\"}", true, getExecutionTimeNs(executionStart), now()));
                } else {
                    sendJson(String.format("{\"result\": %b, \"execution_time_ns\": %d, \"current_time\": \"%s\"}", false, getExecutionTimeNs(executionStart), now()));
                }
            } catch (NumberFormatException e) {
                sendJson("{\"error\": \"wrong query param type\"}");
            } catch (NullPointerException e) {
                sendJson(String.format("{\"error\": \"missed necessary query param\"}"));
            } catch (Exception e) {
                sendJson(String.format("{\"error\": %s}", e.toString()));
            }
        }
    }

    private static long getExecutionTimeNs(Instant executionStart) {
        return (Duration.between(executionStart,Instant.now()).toNanos());
    }

    private static String now() {
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
    }


    private static void sendJson(String jsonDump) {
        System.out.println(String.format(RESPONSE_TEMPLATE, jsonDump.getBytes(StandardCharsets.UTF_8).length, jsonDump));
    }
}
