package edu.escuelaing.app;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;



import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** ESCUELA COLOMBIANA DE INGENIERIA - ARQUITECTURAS EMPRESARIALES

 @author Juan Francisco Teran Roman
 26/08/2023

 DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET
 En este taller usted explorará la arquitectura de las aplicaciones distribuidas. Concretamente, exploraremos la
 arquitectura de  los servidores web y el protocolo http sobre el que están soportados.

 <a href="https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-4">...</a>

 */

// TALLER 4


public class HttpServer {

    private static final HttpServer _instance = new HttpServer();

    private static final Map<String, Method> servicios = new HashMap<>();
    //private static Map<String, ServicioParam> servicios = new HashMap();

    public static HttpServer getInstance(){
        return _instance;
    }

    private OutputStream outputStream;

    /**
     * Crea un socket de servidor web y escucha las conexiones entrantes.
     * El socket del servidor se crea en el puerto 35001 y el servidor escucha las conexiones entrantes hasta que finaliza.
     * Cuando se acepta una conexión, el servidor crea un PrintWriter y un BufferedReader para comunicarse con el cliente (explorador web).
     * El servidor lee datos del cliente y los procesa de acuerdo con la URL especificada (el servior lee el disco local y retorna los archivos solicitados por el cliente).
     * El programa funciona con archivos como HTML, CSS, JavaScript e imágenes JPG del directorio "src/main/resources".
     * Crea una aplicacion web para probar el servidor y que invoca servicios REST de forma asincrona desde el cliente.
     * El servidor provee un framework IoC para la construcción de aplicaciones web a partir de POJOS (beans).
     * El framework explora el directorio raiz buscando clases con la anotación @Component, y carga todos los archivos que tengan dicha anotación.
     *
     * @throws IOException si se produce un error de Input-Output al crear el socket del servidor o al comunicarse con el cliente
     */
    public void start() throws IOException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        Path root = Paths.get("src/main/java/edu/escuelaing/app");
        DirectoryStream<Path> files = Files.newDirectoryStream(root);
        Set<Class<?>> classes = new HashSet<>();
        for (Path file: files){
            if (file.toString().endsWith(".java")){
                //System.out.println("hohohohohohoh");
                String fileName = file.toString().replace("\\", ".").split("java.")[1].split(".java")[0];

                if(Class.forName(fileName).isAnnotationPresent(Component.class)) {
                    //System.out.println("JAJAJAJAJAAJAA");
                    classes.add(fileName.getClass());
                    Class<?> c = Class.forName(fileName);
                    Method[] m = c.getDeclaredMethods();
                    for (Method method : m) {
                        if (method.isAnnotationPresent(GetMapping.class)) {
                            String k = method.getAnnotation(GetMapping.class).value();
                            servicios.put(k, method);
                        }
                    }
                }
            }
            //System.out.println(file + "GUACALA");
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35001);

        } catch (IOException e) {
            System.err.println("Could not listen on port: 35001.");
            System.exit(1);
        }




        while (true) {


            Socket clientSocket = null;
            try {
                System.out.println("\nListo para recibir... \n");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }


            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()
                    ));

            outputStream = clientSocket.getOutputStream();



            String busqueda, respuesta;
            boolean firstLine = true;
            String urlString = "";
            while ((busqueda = in.readLine()) != null) {
                System.out.println("Received: " + busqueda);
                if (firstLine) {
                    firstLine = false;
                    urlString = busqueda.split(" ")[1];
                    System.out.println("Data request: " + busqueda);

                    System.out.println("HOLA" + urlString);
                    // POST

                }

                if (!in.ready()) {
                    break;
                }



            }

            if(servicios.containsKey(urlString)){
                //Method method = servicios.get(urlString);
                System.out.println(urlString+"dgnhjkdfngjkd");
                respuesta = String.valueOf(servicios.get(urlString).invoke(null));
            }

            else{
                //respuesta = servicios.get("/getHello").invoke(null).toString();
                respuesta = String.valueOf(servicios.get("/getHello").invoke(null));
            }



            out.println(respuesta);


            out.close();
            in.close();
            clientSocket.close();

            if (urlString.equals("/salir.exe")){
                System.out.println("Cerrando programa...");
                break;
            }

        }
        serverSocket.close();
    }

    /**
     * Este método devuelve el contenido de un archivo especificado por su URL.
     * El contenido se devuelve como una cadena en formato de respuesta HTTP.
     *
     * @return el contenido del archivo en formato de respuesta HTTP
     */


    public OutputStream getOutputStream() {
        return this.outputStream;
    }


}

