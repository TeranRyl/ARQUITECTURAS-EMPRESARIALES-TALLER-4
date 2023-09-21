package edu.escuelaing.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MyApp {

    /**
     * Este es el método principal, el cual inicia el servidor HTTP.
     *
     * @param args argumentos de línea de comando
     * @throws IOException si ocurre un error de E/S
     */

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        HttpServer.getInstance().start();

    }
}
