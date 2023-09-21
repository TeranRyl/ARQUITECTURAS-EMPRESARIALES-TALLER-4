package edu.escuelaing.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.DirectoryStream;

public class MySparkApp {

    /**
     * Este es el método principal que registra los controladores de ruta y
     * inicia el servidor HTTP.
     *
     * @param args argumentos de línea de comando
     * @throws IOException si ocurre un error de E/S
     */

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        /*HttpServer.register("/hello", str -> HttpServer.getHello(str));
        HttpServer.register("/getFile", str -> {
            try {
                return HttpServer.getFile(str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });*/
        HttpServer.getInstance().start(args);

    }
}
