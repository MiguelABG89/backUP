package Code;

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
					"2. Crear BackUp incremental" );

			opcion = pedirCadena("Introduce una opción");
			switch (opcion) {
				case "0" -> salir = true;
				case "1" -> generarBackUp();
				//case "5" ->
				default -> System.out.println("Opción incorrecta");
			}
		} while (!salir);


	}


	public static void copiarArchivo(Path rutaO,Path rutaF) {
		try{
			Files.copy(rutaO, rutaF,StandardCopyOption.REPLACE_EXISTING);
		}catch (FileNotFoundException e) {
			System.out.println("No se he encontrado el archivo");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean archivoCambiado(Path NombreArchi) {
		String rutaBackUp ="src/recursos/Backups/Backup2022-09-30-09.57.01/";

		Path archivoBUT=Paths.get(rutaBackUp,NombreArchi.getFileName().toString());
		boolean Resultado=false;	

			if(!Files.exists(archivoBUT)) {
				Resultado=true;
			} else
				try {
					if(Files.getLastModifiedTime(archivoBUT).compareTo(Files.getLastModifiedTime(NombreArchi))>0) {
						Resultado=false;
					}
				}catch(SecurityException e){
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
		return Resultado;
	}
	public static void generarBackUp(){
		//Inicializamos las variables donde se encuentran los path
		String Date=new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
		Path rutaOrigen= Path.of("src/recursos/data");
		Path rutaFin=Path.of("src/recursos/Backups/Backup"+Date);
		//Lista de archivos para copiar
		DirectoryStream<Path> Lista= null;
		try {
			Lista = Files.newDirectoryStream(rutaOrigen);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		boolean Condicion=false;
		//creamos el directorio destino
		if(Files.exists(rutaFin)) {
			//bucle para recorrer los archivos
			for(Path archOrigen:Lista){

				if(!Files.isDirectory(archOrigen)){

					Condicion=archivoCambiado(archOrigen);

					if(Condicion=true) {

						Path archFin=Path.of(rutaFin+"/"+archOrigen.getFileName());
						copiarArchivo(archOrigen,archFin);

					}
				}

			}
		}else System.out.println("No se ha hecho ninguna modificacion");
	}
	public static void generarBackUPincremental(){

	}

}
