package Code.codigo;

import Code.libs.CheckFiles;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackUpIncremental {
    public static void generarBackUPincremental() {
        String Date = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
        Path rutaOrigen = Path.of("src/recursos/data");
        Path rutaBackUp = Path.of("src/recursos/Backups/Backup");
        Path rutaFin = Path.of("src/recursos/Backups/Backup" + Date);

        //gestion de las rutas
        if (CheckFiles.ficheroLegible(rutaOrigen)){
            System.out.println("La ruta origen es legible");
        }
        if (CheckFiles.ficheroLegible(rutaBackUp)){
            System.out.println("la ruta BackUpCompleto es legible");
        }

        //Lista de archivos para copiar
        DirectoryStream<Path> Lista = null;
        try {
            Lista = Files.newDirectoryStream(rutaOrigen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Creamos la carpeta con la fecha
        try {
            Files.createDirectories(rutaFin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //creamos el directorio destino
        if (Files.exists(rutaFin)) {
            //bucle para recorrer los archivos
            for (Path archOrigen : Lista) {

                if (!Files.isDirectory(archOrigen)) {

                    Path archivoBUT = Paths.get(rutaBackUp.toString(), archOrigen.getFileName().toString());

                    try {
                        //condicion del IF para comprobar las modificaciones por fechas
                        if (!Files.exists(archivoBUT)) {
                            Path archFin = Path.of(rutaFin + "/" + archOrigen.getFileName());
                            copiar.copiarArchivo(archOrigen, archFin);
                        }else {
                            if (Files.getLastModifiedTime(archivoBUT).compareTo(Files.getLastModifiedTime(archOrigen)) < 0){
                                Path archFin = Path.of(rutaFin + "/" + archOrigen.getFileName());
                                copiar.copiarArchivo(archOrigen, archFin);
                            }
                        }
                    } catch (SecurityException e) {
                        //error por no tener permisos en esa carpeta
                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                }

            }
        } else System.out.println("No se ha hecho ninguna modificacion");
    }
}
