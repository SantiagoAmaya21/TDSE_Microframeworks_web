package edu.eci.tdse.framework;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String path;
    private Map<String, String> queryParams = new HashMap<>();

    public Request(String path, String queryString) {
        this.path = path;
        parseQueryParams(queryString);
    }

    private void parseQueryParams(String queryString) {
        if (queryString == null || queryString.isEmpty()) return;

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                queryParams.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public String getPath() {
        return path;
    }

    public String getValues(String key) {
        return queryParams.get(key);
    }
}
