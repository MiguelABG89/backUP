package Code;

import Code.codigo.BackUp;
import Code.codigo.BackUpIncremental;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Code.libs.Leer.pedirCadena;

public class EjBackUp2 {

    public static void main(String[] args) {

        boolean salir = false;
        String opcion = "";
        do {
            System.out.println("0. Salir \n" +
                    "1. Crear BackUp \n" +
                    "2. Crear BackUp incremental");

            opcion = pedirCadena("Introduce una opción");
            switch (opcion) {
                case "0" -> salir = true;
                case "1" -> BackUp.generarBackUp();
                case "2" -> BackUpIncremental.generarBackUPincremental();
                default -> System.out.println("Opción incorrecta");
            }
        } while (!salir);


    }
}
