package edu.eci.tdse.framework;


@FunctionalInterface
public interface Route {
    String handle(Request req, Response res);
}