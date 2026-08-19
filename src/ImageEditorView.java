import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageEditorView extends JFrame {
  JPanel mainPanel = new JPanel(new FlowLayout());
  JButton loadImageButton = new JButton("Load Image");
  JFileChooser inputImageChooser = new JFileChooser();
  FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
  JLabel selectedImageLabel = new JLabel("Selected image: none");

  public ImageEditorView() {
    // We are extending the JFrame class, so we MUST call the parent constructor.
    super("Image Editor");

    // methods on the parent JFrame class
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    inputImageChooser.setFileFilter(filter);

    mainPanel.add(loadImageButton);
    mainPanel.add(selectedImageLabel);

    // add the main panel and make the window visible
    add(mainPanel);
  }

  // ################## A section to register action listeners ################
  public void addLoadImageListener(ActionListener listener) {
    loadImageButton.addActionListener(listener);
  }

  public void addInputImageChooserListener(ActionListener listener) {
    inputImageChooser.addActionListener(listener);
  }

  // ############### A section to trigger actions in the GUI ##################
  public File showInputImageChooser() {
    int returnVal = inputImageChooser.showOpenDialog(this);
    if (returnVal != JFileChooser.APPROVE_OPTION) {
      return null;
    }

    return inputImageChooser.getSelectedFile();
  }

  public void showSelectedInputImageText(String text) {
    selectedImageLabel.setText(text);
  }
}
