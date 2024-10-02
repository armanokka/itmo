import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

public class Request {
    private final HashMap<String, Object> body;
    private final HashMap<String, Object> parameters;

    public Request() {
        this.parameters = new HashMap<>();
        this.body = new HashMap<>();
    }

    public Request fillParameters(Properties params) {
        String queryString = params.getProperty("QUERY_STRING");
        if (queryString != null && !queryString.isEmpty()) {
            for (String pair : queryString.split("&")) {
                String[] keyValue = pair.split("=");
                if (keyValue.length > 1) {
                    parameters.put(keyValue[0], keyValue[1]);
                } else {
                    parameters.put(keyValue[0], "");
                }
            }
        }
        return this;
    }

    public Request fillBody(Properties params) {
        String contentLength = params.getProperty("CONTENT_LENGTH");
        int length = (contentLength != null) ? Integer.parseInt(contentLength) : 0;

        if (length > 0) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                char[] bodyChars = new char[length];
                reader.read(bodyChars, 0, length);
                String requestBody = new String(bodyChars);
                parseBody(requestBody);
            } catch (Exception e) {
                body.put("error", "Ошибка при чтении тела запроса");
            }
        }
        return this;
    }

    private void parseBody(String requestBody) {
        if (requestBody != null && !requestBody.isEmpty()) {
            for (String pair : requestBody.split("&")) {
                String[] keyValue = pair.split("=");
                if (keyValue.length > 1) {
                    body.put(keyValue[0], keyValue[1]);
                } else {
                    body.put(keyValue[0], "");
                }
            }
        }
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body.clear();
        this.body.putAll(body);
    }

    public String getBodyAsString() {
        return body.toString();
    }
}