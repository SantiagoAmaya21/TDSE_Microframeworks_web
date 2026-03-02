package edu.eci.tdse.framework;

import edu.eci.tdse.framework.HttpServer;

import java.util.HashMap;
import java.util.Map;

public class WebFramework {

    private static Map<String, Route> routes = new HashMap<>();
    private static String staticFolder = "webroot";

    public static void get(String path, Route route) {
        routes.put(path, route);
    }

    public static Route getRoute(String path) {
        return routes.get(path);
    }

    public static void staticfiles(String folder) {
        staticFolder = folder;
    }

    public static String getStaticFolder() {
        return staticFolder;
    }

    public static void start(int port) {
        HttpServer server = new HttpServer(port);
        server.start();
    }
}
