package edu.eci.tdse.framework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream()
        ) {

            String inputLine = in.readLine();
            if (inputLine == null) return;

            String[] requestParts = inputLine.split(" ");
            String fullPath = requestParts[1];

            String path = fullPath;
            String query = null;

            if (fullPath.contains("?")) {
                path = fullPath.substring(0, fullPath.indexOf("?"));
                query = fullPath.substring(fullPath.indexOf("?") + 1);
            }

            Request request = new Request(path, query);
            Response response = new Response();

            Route route = WebFramework.getRoute(path);

            if (route != null) {
                String body = route.handle(request, response);
                sendResponse(out, body, response.getContentType(), response.getStatus());
            } else {
                serveStaticFile(out, path);
            }

            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(OutputStream out, String body, String contentType, int status) throws IOException {
        PrintWriter writer = new PrintWriter(out, true);

        writer.println("HTTP/1.1 " + status + " OK");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + body.length());
        writer.println();
        writer.println(body);
    }

    private void serveStaticFile(OutputStream out, String path) throws IOException {

        String staticFolder = WebFramework.getStaticFolder();

        if (path.equals("/")) {
            path = "/index.html";
        }

        String filePath = "target/classes" + staticFolder + path;
        File file = new File(filePath);

        if (file.exists() && !file.isDirectory()) {
            byte[] fileBytes = java.nio.file.Files.readAllBytes(file.toPath());

            out.write(("HTTP/1.1 200 OK\r\n").getBytes());
            out.write(("Content-Type: text/html\r\n").getBytes());
            out.write(("Content-Length: " + fileBytes.length + "\r\n\r\n").getBytes());
            out.write(fileBytes);
        } else {
            String notFound = "404 Not Found";
            sendResponse(out, notFound, "text/plain", 404);
        }
    }
}
