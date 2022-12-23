package ch.bfh.java.experiments.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class HttpServer {

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server ready on port " + SERVER_PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {
                Scanner scanner = new Scanner(inputStream);
                PrintWriter writer = new PrintWriter(outputStream, true);

                if (scanner.hasNext()) {
                    String[] requestLine = scanner.nextLine().split(" ");
                    requestLine[1] = URLDecoder.decode(requestLine[1], StandardCharsets.UTF_8);

                    System.out.println("Request: " + String.join(" ", requestLine));

                    if (!requestLine[0].startsWith("GET")) {
                        error405Response(writer);
                        continue;
                    }

                    String path = requestLine[1];
                    if (!path.endsWith("\\")) {
                        path += "index.html";
                    }

                    Path file = Paths.get("src\\main\\resources\\web", path);
                    if (!Files.isRegularFile(file)) {
                        error404Response(writer);
                    } else {
                            okResponse(writer, outputStream, file);
                    }
                }
            } finally {
                socket.close();
            }
        }
    }

    private static void error404Response(PrintWriter writer) {
        writer.println("HTTP/1.0 404 File Not Found");
        writer.println();
    }

    private static void error405Response(PrintWriter writer) {
        writer.println("HTTP/1.0 405 Method Not Allowed");
        writer.println();
    }

    private static void okResponse(PrintWriter writer, OutputStream outputStream, Path filePath) throws IOException {
        writer.println("HTTP/1.0 200 OK");
        writer.println("Content-Type: " + Files.probeContentType(filePath));
        writer.println("Content-Length: " + Files.size(filePath));
        writer.println();
        outputStream.write(Files.readAllBytes(filePath));
    }
}
