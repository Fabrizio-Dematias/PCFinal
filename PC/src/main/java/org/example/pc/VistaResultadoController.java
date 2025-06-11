package org.example.pc;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class VistaResultadoController {

    @FXML private ImageView imagenProcesada1, imagenProcesada2, imagenProcesada3, imagenProcesada4, imagenProcesada5;

    private Image[] imagenes;

    public void cargarImagenes(List<Image> lista) {
        imagenes = new Image[5];
        for (int i = 0; i < lista.size(); i++) {
            imagenes[i] = lista.get(i);
            getImageView(i).setImage(imagenes[i]);
        }
    }

    private ImageView getImageView(int index) {
        return switch (index) {
            case 0 -> imagenProcesada1;
            case 1 -> imagenProcesada2;
            case 2 -> imagenProcesada3;
            case 3 -> imagenProcesada4;
            case 4 -> imagenProcesada5;
            default -> null;
        };
    }

    public void descargarImagen(int index) {
        if (imagenes[index] == null) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar imagen procesada");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File archivo = fileChooser.showSaveDialog(null);
        if (archivo != null) {
            // Lógica de guardado a implementar más adelante con WritableImage
            System.out.println("Simulando descarga de imagen " + (index + 1));
        }
    }

    public void descargarImagen1() { descargarImagen(0); }
    public void descargarImagen2() { descargarImagen(1); }
    public void descargarImagen3() { descargarImagen(2); }
    public void descargarImagen4() { descargarImagen(3); }
    public void descargarImagen5() { descargarImagen(4); }
}
