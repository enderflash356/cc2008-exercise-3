import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageEditorController {
    private ImageEditorView view;
    private ImageEditorModel model;

    public ImageEditorController(ImageEditorModel model, ImageEditorView view) {
        this.view = view;
        this.model = model;

        // hookup action listeners
        this.view.addLoadListener(e -> handleLoadImage());
        this.view.addSaveListener(e -> handleSaveImage());
        this.view.addNegativoListener(e -> handleFiltro("Negativo"));
        this.view.addGrayscaleListener(e -> handleFiltro("Escala de grises"));
        this.view.addBrilloListener(e -> handleFiltro("Brillo"));
        this.view.addEspejoListener(e -> handleFiltro("Espejo Horizontal"));
        this.view.addRotarListener(e -> handleFiltro("Rotar 90 grados"));
        this.view.addDeshacerListener(e -> handleDeshacer());
        this.view.addReiniciarListener(e -> handleReiniciar());
    }
        

    public void handleLoadImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Imagenes JPG", "jpg", "jpeg", "png"));
        int result = chooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            try {
                Image loaded = ImageUtils.load(selected.getAbsolutePath());
                model.setInputImage(loaded, selected.getName());
                view.setStatus("Imagen cargada" + selected.getName());
            } catch (Exception ex) {
                view.showErrorDialog("Error al cargar imagen: " + ex.getMessage());
            } finally {
                refresh();
            }
        }
    }

    
    private void handleSaveImage() {
        try {
            Image current = model.getCurrentImage();
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showSaveDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selected = chooser.getSelectedFile();
                String path = selected.getAbsolutePath();
                if (!path.endsWith(".png") && !path.endsWith(".jpg")){
                    path += ".png";
                }
                ImageUtils.save(current, path);
                view.setStatus("Imagen guardada en " + path);
            }

        }catch (Exception ex) {
                view.showErrorDialog("Error al guardar imagen: " + ex.getMessage());
            } finally {
                refresh();
            }
        }
    private void handleFiltro(String tipo) {
        try {
            Image actual = model.getCurrentImage();
            ImageEditor editor = new ImageEditor(actual);
            Image resultado = null;
            
            switch (tipo) {
                case "Negativo":
                    resultado = editor.negative();
                    break;
                case "Escala de grises":
                    resultado = editor.grayscale();
                    break;
                case "Brillo":
                    resultado = editor.brightness(30);
                    break;
                case "Espejo Horizontal":
                    resultado = editor.mirrorHorizontal();
                    break;
                case "Rotar 90 grados":
                    resultado = editor.rotate90();
                    break;
            }

            model.aplicarFiltro(tipo, resultado);
            view.setStatus("Filtro " + tipo + " aplicado");
        } catch (SinImgExcept ex) {
            view.showErrorDialog(ex.getMessage());
        } catch (Exception ex) {
            view.showErrorDialog("Error al aplicar filtro: " + ex.getMessage());
        } finally {
            refresh();
        }
    }

    private void handleDeshacer() {
        try {
            model.deshacer();
            view.setStatus("Imagen deshacada");
        } catch (HistVacExcept ex) {
            view.showErrorDialog(ex.getMessage());
        } finally {
            refresh();
        }
    }
    private void handleReiniciar() {
        try {
            model.reiniciar();
            view.setStatus("Reiniciado");
        } catch (SinImgExcept ex) {
            view.showErrorDialog(ex.getMessage());
        } finally {
            refresh();
        }
    }

    public void refresh() {
        try {
            view.setOriginalImage(ImageUtils.toBufferedImage(model.getOriginalImage()));
            view.setProcesadaImage(ImageUtils.toBufferedImage(model.getCurrentImage()));
        } catch (SinImgExcept ex) {
            
        } 
        view.updateHistorialView(model.getOperaciones().size(), model.getOperaciones());
    }
}

    
