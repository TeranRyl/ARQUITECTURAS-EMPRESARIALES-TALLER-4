# MICROFRAMEWORKS WEB

Un servidor web que permite construir aplicaciones web de manera simple usando funciones lambda.

## Instrucciones para ejecutar

A continuacion, dejo respectivas instrucciones para correr el proyecto adecuadamente tras obtener la direccion a este repositorio GitHub. Igualmente, mas abajo dejare evidencia detallada para garantizar que se entienda su implementacion. La aplicacion debe usarse para fines de prueba y desarrollo.

### Requisitos previos

Para descargar la aplicacion, ya estando aqui, se necesita un equipo de computo con las siguientes caracteristicas:

```
- Java instalado

- Maven instalado

- JavaScript instalado

- Conexion a internet

- Explorador web

- (RECOMENDACION) Tener todo actualizado
```

### Instalando

Paso a paso

```
1. Descargar el codigo: Bajar el .ZIP correspondiente al repositorio.

2. Extraer el contenido del archivo comprimido.

3. Abrir el Shell de su preferencia.

4. Desde el Shell, muevase a la ubicacion donde extrajo el archivo .ZIP (Deberia estar dentro de la carpeta llamada  "ARQUITECTURAS-EMPRESARIALES-TALLER-3-master").

5. Desde el Shell, escriba "mvn package" (este comando compila, construye y empaqueta el proyecto en un .JAR).

6. Desde el Shell, escriba "java -cp target/Taller3-1.0-SNAPSHOT.jar edu.escuelaing.app.MySparkApp" para ejecutar la aplicacion.
   Deberia ver un mensaje diciendo "Listo para recibir... ".

7. Abra su explorador web de preferencia y busque en una pestaña incognita lo siguiente:
   
   - "localhost:35001/getFile?name=terminos.html" (SIN LAS COMILLAS) - Pagina html con ruta "src/main/resources/terminos.html".
   - "localhost:35001/getFile?name=style.css" (SIN LAS COMILLAS) - Archivo css con ruta "src/main/resources/style.css".
   - "localhost:35001/getFile?name=app.js" (SIN LAS COMILLAS) - Archivo javascript con ruta "src/main/resources/app.js".
   - "localhost:35001/web/index.html" (SIN LAS COMILLAS) - Aplicacion con varios tipos de archivos 
      incluidos.
   - "localhost:35001/getFile?name=risas.jpg" (SIN LAS COMILLAS) - Imagen jpg con ruta "src/main/resources/risas.jpg".

   - "localhost:35001/hello?name=Juan" (SIN LAS COMILLAS) - Servicio web que retorna "Hello" + *el       parametro que se pase tras el parametro "name".

NOTA: Para abortar el shell con el servidor encendido, podemos presionar "CTRL" + "C".
```

Una vez haya terminado, puede cerrar el servidor introduciendo, desde el cliente, la URL "localhost:35001/web/salir.exe" (SIN LAS COMILLAS) y el servidor se apagara enseguida.



## Evaluacion

Ejemplo de desarrollo de aplicaciones en el servidor:

Inicialmente, el programador desea leer el contenido de su archivo "terminos.html", entonces lo sube a la carpeta "src/main/resources/".

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-3/assets/81679109/7edb899c-07a5-4bc8-805b-92152a15621e)

Posteriormente, agrega el codigo necesario para entregar archivo HTML.

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-3/assets/81679109/b729f503-c25d-4989-ae04-e2fc3f89d0b5)

Finalmente, se agrega la instruccion para obtener el archivo en el metodo "main" del proyecto, de la siguiente manera para activar el servicio:

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-3/assets/81679109/4720a670-bca7-4d22-a21d-fcf876f2ff0b)

Este seria el resultado, buscando por la siguiente URI que abre el puerto 35001 y devuelve el archivo "terminos.html": http://localhost:35001/getFile?name=terminos.html desde Windows.

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-3/assets/81679109/e35b614d-8bad-4a96-a5ea-bf0eae784873)









## Implementacion

NOTA1

En el backend se utilizo codigo fuente puro Java (sin ningun tipo de framework). Se utilizo JavaScript asincrono como cliente web ejecutado como explorador web.
Tambien se utilizaron sockets, los cuales tienen la funcion de comunicar dos programas: un cliente y un servidor. El proposito de esto es poder conectarse al servidor web, por lo que el cliente y servidor se conectaron entre si a traves de sus sockets "cliente" (el servidor abre otro socket adicional). Para esto, el cliente debe buscar comunicarse con el servidor especificando la IP destino a la que pretende conectarse, y por cual puerto, mientras que el servidor abre su respectivo puerto.
Para implementar el servidor web se utilizo HTTP.
El explorador web se conecto al servidor web por medio de sockets (los cuales utilizan el protocolo TCP -> Orientado a Conexion).
El flujo es el siguiente: Se abre la conexion TCP -> El explorador web envia solicitud HTTP al servidor -> El servidor responder al explorador web -> Cierre de conexion.


NOTA 2

El explorador pide y ejecuta lo que el servidor web le envie, y luego el servidor devuelve el resultado (paginas html, archivos css y js e imagenes jpg).
Para esta conversion, se tiene en cuenta el encabezado del archivo y se devuelve en bytes. Este proceso varia segun el tipo de contenido.
Main permite crear una aplicacion de backend, la cual estara en el servidor web, y funciona como servidor de aplicaciones (el cual permite alojar distintas aplicaciones web en el), en este caso las aplicaciones de prueba se encuentran en la carpeta "resources".
Se hace uso del patron de diseño SINGLETON, para instanciar por unica vez "HttpServer".
Se registran servicios web usando funciones lambda, utilizando formularios para invocarlos.
Por protocolo, en el parametro se manda todo el Query, de tal manera que el programador se encarga de sacar los valores "hello?name=". Los parametros del query se pueden leer desde el programa.


## Construido con

* [Java](https://www.oracle.com/co/java/) - Backend
* [Maven](https://maven.apache.org/) - Gestion de ciclo de vida y dependencias
* [Git/Github](https://git-scm.com/) - Almacenar el codigo fuente
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE para desarrollo

## Autores

* **Juan Francisco Teran** - *Trabajo total* - [TeranRyl](https://github.com/TeranRyl)

## Licencia

Este proyecto tiene la licencia GNU General Public License v3.0; consulte el archivo [LICENSE](LICENSE.txt) para obtener más información.

## Reconocimientos

* PurpleBooth - Plantilla para hacer un buen README
* Luis Daniel Benavides - Preparacion para el taller e introduccion al diseño de sistemas de informacion

