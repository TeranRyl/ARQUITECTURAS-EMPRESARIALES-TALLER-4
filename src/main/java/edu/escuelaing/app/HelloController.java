package edu.escuelaing.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class HelloController {

    @GetMapping("/getHello")
    public static String getHello() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/terminos.html")
    public static String getTerminos() throws IOException {
        String response;
        Path rutaHtml = Paths.get("src/main/resources/terminos.html");
        String leerHtml = Files.readString(rutaHtml);
        return  response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                // +"Hola";
                + leerHtml;
    }

    @GetMapping("/index.html")
    public static String getIndex() throws IOException {
        String response;
        Path rutaIndex = Paths.get("src/main/resources/index.html");
        String leerIndex = Files.readString(rutaIndex);
        return response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                // +"Hola";*/
                + leerIndex;
    }

    @GetMapping("/style.css")
    public static String getStyle() throws IOException {
        String response;
        Path rutaCss = Paths.get("src/main/resources/style.css");
        String leerCss = Files.readString(rutaCss);
        return response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/css\r\n"
                + "\r\n"
                + leerCss;

    }

    @GetMapping("/app.js")
    public static String getApp() throws IOException {
        String response;
        Path rutaJs = Paths.get("src/main/resources/app.js");
        String leerJs = Files.readString(rutaJs);
        return response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/javascript\r\n"
                + "\r\n"
                + leerJs;

    }

    @GetMapping("/risas.jpg")
    public static String getRisas() throws IOException{
        String response;
        Path rutaJpg = Paths.get("src/main/resources/risas.jpg");
        response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: image/jpeg\r\n"
                + "\r\n";
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/risas.jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServer server = HttpServer.getInstance();
        DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        dataOutputStream.writeBytes(response);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return response;

    }

}
