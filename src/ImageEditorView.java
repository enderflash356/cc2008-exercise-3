import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageEditorView extends JFrame {
    JPanel mainPanel = new JPanel();
    JButton loadImageButton = new JButton("Load Image");
    JFileChooser inputImageChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
    ImagePanel imagePanel;

    public ImageEditorView() {
        // We are extending the JFrame class, so we MUST call the parent constructor.
        super("Image Editor");

        // orientation of main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // methods on the parent JFrame class
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputImageChooser.setFileFilter(filter);

        mainPanel.add(loadImageButton);

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

    public void showInputImage(BufferedImage image) {
        if (imagePanel != null) {
            mainPanel.remove(imagePanel);
        }

        imagePanel = new ImagePanel(image);
        imagePanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.add(imagePanel);
        pack();
    }
}
