package edu.eci.tdse.framework;

import static edu.eci.tdse.framework.WebFramework.*;
import static edu.eci.tdse.framework.WebFramework.staticfiles;

public class Main {
    public static void main(String[] args) {

        staticfiles("/webroot");

        get("/hello", (req, res) ->
                "Hello " + req.getValues("name")
        );

        get("/pi", (req, res) ->
                String.valueOf(Math.PI)
        );

        start(8080);
    }
}