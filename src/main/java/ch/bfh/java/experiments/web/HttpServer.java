package ch.bfh.java.experiments.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

                    File file = new File("src\\main\\resources\\web" + requestLine[1]);
                    if (!file.exists()) {
                        error404Response(writer);
                        continue;
                    } else {
                        if (file.isFile()) {
                            System.out.println("it is a file");
                            okResponse(writer, outputStream, file);
                        }
                        if (file.isDirectory()) {
                            System.out.println("it is a dir");
                            File possibleIndexFile = new File(file.getPath() + "/index.html");
                            if (possibleIndexFile.exists()) {
                                okResponse(writer, outputStream, file);
                            } else {
                                error404Response(writer);
                            }
                        }
                    }
                }
				/*writer.println("HTTP/1.0 200 OK");
				writer.println();
				writer.println("<h1>Hello World!</h1>");
				writer.println(new Date());*/
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

    private static void okResponse(PrintWriter writer, OutputStream outputStream, File file) {
        writer.println("HTTP/1.0 200 OK");
        writer.println();
        if (file != null) {
            try {
                String[] splittedFileName = file.getName().split("\\.");
                String ending = splittedFileName[splittedFileName.length - 1];
                if (ending.equals("html") || ending.equals("css")) {
                    writer.println(String.join("\n", Files.readAllLines(file.toPath())));
                } else {
                    outputStream.write(Files.readAllBytes(file.toPath()));
                    outputStream.flush();
                }
            } catch (IOException e) {
                System.out.println("Error occured: " + e.getMessage());
            }

        }
    }
}
