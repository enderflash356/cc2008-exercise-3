import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    // must come before any component is created
    FlatLightLaf.setup();

    SwingUtilities.invokeLater(() -> {
      // Instantiate our 3 separate MVC classes
      ImageEditorModel model = new ImageEditorModel();
      ImageEditorView view = new ImageEditorView();
      new ImageEditorController(model, view);

      // all ready, make the window visible
      view.setVisible(true);
    });
  }
}
