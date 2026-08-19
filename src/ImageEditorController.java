import java.io.File;

public class ImageEditorController {
  private ImageEditorView view;
  private ImageEditorModel model;

  public ImageEditorController(ImageEditorModel model, ImageEditorView view) {
    this.view = view;
    this.model = model;

    // hookup action listeners
    this.view.addLoadImageListener(e -> handleLoadImage());
  }

  public void handleLoadImage() {
    File selectedFile = view.showInputImageChooser();
    if (selectedFile == null) {
      return;
    }

    // mutate the application state
    model.setInputFileName(selectedFile.getAbsolutePath());

    // we updated the state of the model, we must re-draw the view layer
    refresh();
  }

  // call the view to re-draw the application state
  private void refresh() {
    view.showSelectedInputImageText(model.getInputFileName());
  }
}
