package com.Eventos.Teo.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileService {

    // Nombre de la carpeta donde se guardarán las fotos
    private final String DIRECTORIO = "uploads";

    public String guardarArchivo(MultipartFile archivo) {
        try {
            // 1. Obtener la ruta absoluta de la carpeta 'uploads'
            Path rutaDirectorio = Paths.get(DIRECTORIO).toAbsolutePath();

            // 2. Crear la carpeta si no existe
            if (!Files.exists(rutaDirectorio)) {
                Files.createDirectories(rutaDirectorio);
            }

            // 3. Generar un nombre único para evitar duplicados
            String nombreUnico = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Path rutaCompleta = rutaDirectorio.resolve(nombreUnico);

            // 4. Copiar el archivo al servidor
            Files.copy(archivo.getInputStream(), rutaCompleta);

            return nombreUnico; // Devolvemos el nombre para guardarlo en la DB
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen en el disco: " + e.getMessage());
        }
    }

    public void eliminarArchivo(String nombreArchivo) {
        Path rutaArchivo = Paths.get(DIRECTORIO).resolve(nombreArchivo).toAbsolutePath();
        try {
            Files.deleteIfExists(rutaArchivo);
        } catch (IOException e) {
            System.err.println("No se pudo borrar el archivo físico: " + e.getMessage());
        }
    }
}
