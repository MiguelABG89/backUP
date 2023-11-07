package Code.codigo;

import Code.libs.CheckFiles;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackUp {
    public static void generarBackUp() {
        //Inicializamos las variables donde se encuentran los path
        String Date = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
        Path rutaOrigen = Path.of("src/recursos/data");
        Path rutaFin = Path.of("src/recursos/Backups/Backup");

        //Control de excepciones de escritura

        if (CheckFiles.ficheroLegible(rutaOrigen)){
            System.out.println("La ruta origen es legible");
        }
        if (CheckFiles.ficheroLegible(rutaFin)){
            System.out.println("la ruta fin es legible");
        }

        //Lista de archivos para copiar
        DirectoryStream<Path> Lista = null;
        try {
            Lista = Files.newDirectoryStream(rutaOrigen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //creamos el directorio destino
        if (Files.exists(rutaOrigen)) {
            //bucle para recorrer los archivos
            for (Path archOrigen : Lista) {

                if (!Files.isDirectory(archOrigen)) {

                    Path archFin = Path.of(rutaFin + "/" + archOrigen.getFileName());
                    copiar.copiarArchivo(archOrigen, archFin);
                }

            }
        } else System.out.println("No se ha hecho ninguna modificacion");

    }
}
