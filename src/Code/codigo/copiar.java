package Code.codigo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class copiar {
    //metodo para realizar la copia de los archivos
    public static void copiarArchivo(Path rutaO, Path rutaF) {
        try {
            Files.copy(rutaO, rutaF, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("No se he encontrado el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
