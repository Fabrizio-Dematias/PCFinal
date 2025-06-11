package org.example.pc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;


import java.io.File;

public class ControladorCarga {

    @FXML private Button botonCargar;
    @FXML private Button botonProcesar;
    @FXML private ImageView imagen1, imagen2, imagen3, imagen4, imagen5;

    private ImageView[] vistas;

    @FXML
    public void initialize() {
        vistas = new ImageView[]{imagen1, imagen2, imagen3, imagen4, imagen5};

        botonProcesar.setDisable(false);

        botonCargar.setOnAction(e -> cargarImagenes());

        botonProcesar.setOnAction(e -> mostrarVistaResultado());
    }

    private void cargarImagenes() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imágenes");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.bmp")
        );

        // Permitir selección múltiple
        java.util.List<File> archivos = fileChooser.showOpenMultipleDialog(null);

        if (archivos != null && !archivos.isEmpty()) {
            int cantidad = Math.min(archivos.size(), vistas.length);
            for (int i = 0; i < cantidad; i++) {
                Image imagen = new Image(archivos.get(i).toURI().toString());
                vistas[i].setImage(imagen);
            }
            // Limpiar los restantes si antes había imágenes
            for (int i = cantidad; i < vistas.length; i++) {
                vistas[i].setImage(null);
            }
        }
    }

    private void mostrarVistaResultado() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pc/vista-resultado.fxml"));
            Parent root = loader.load();

            // Pasar las imágenes cargadas
            VistaResultadoController controlador = loader.getController();
            List<Image> lista = new ArrayList<>();
            for (ImageView view : vistas) {
                if (view.getImage() != null) lista.add(view.getImage());
            }
            controlador.cargarImagenes(lista);

            Stage stage = new Stage();
            stage.setTitle("Vista Previa - Imágenes Procesadas");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}