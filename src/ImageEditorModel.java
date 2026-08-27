import java.util.ArrayList;
import java.util.List;

public class ImageEditorModel {
    private String inputFileName;
    private Image originalImage;
    private Image currentImage;
    private List<Image> historial;
    private List<String> operaciones;

    public ImageEditorModel(){
        this.historial = new ArrayList<>();
        this.operaciones = new ArrayList<>();
    }

    public void setInputImage(Image image, String name) {
        this.originalImage = image;
        this.currentImage = image;
        this.inputFileName = name;
        this.historial.clear();
        this.operaciones.clear();
    }

    public Image getCurrentImage() throws SinImgExcept {
        if (currentImage == null) {
            throw new SinImgExcept("No hay imagen cargada en el programa");
        }
        return currentImage;
    }

    public Image getOriginalImage() throws SinImgExcept {
        if (originalImage == null) {
            throw new SinImgExcept("No hay imagen original cargada");
        }
        return originalImage;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public List<String> getOperaciones() {
        return operaciones;
    }

    public int getHistorialSize() {
        return historial.size();
    }

    public void aplicarFiltro(String nombreFiltro, Image nuevaImage) throws SinImgExcept {
        if (currentImage == null) {
            throw new SinImgExcept("No hay imagen cargada en el programa, debe cargar una imagen antes de aplicar filtros");
        }
        historial.add(currentImage);
        operaciones.add(nombreFiltro);
        currentImage = nuevaImage;
    }

    public void deshacer() throws HistVacExcept {
        if (historial.isEmpty()) {
            throw new HistVacExcept("No hay historial de imagenes");
        }
        int ultimoIndice = historial.size() - 1;
        currentImage = historial.remove(ultimoIndice);
        operaciones.remove(ultimoIndice);
    }

    public void reiniciar() throws SinImgExcept {
        if (originalImage == null) {
            throw new SinImgExcept("No hay imagen para reiniciar");
        }
        currentImage = originalImage;
        historial.clear();
        operaciones.clear();
    }
}
