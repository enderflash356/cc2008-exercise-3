import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.List;

public class ImageEditorView extends JFrame {
    private JButton btnCargar;
    private JButton btnGuardar;
    private JButton btnNegativo;
    private JButton btnEscalaGris;
    private JButton btnBrillo;
    private JButton btnEspejo;
    private JButton btnRotar;
    private JButton btnDeshacer;
    private JButton btnReiniciar;

    private ImagePanel panelOriginal;
    private ImagePanel panelProcesado;
    private JLabel statusLabel;
    private JTextArea historialArea;

    public ImageEditorView(){
        setTitle("Editor de imágenes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCargar = new JButton("Cargar imagen");
        btnGuardar = new JButton("Guardar imagen");
        topPanel.add(btnCargar);
        topPanel.add(btnGuardar);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1,2, 10, 10));
        JPanel p1 = new JPanel(new BorderLayout());
        p1.setBorder(BorderFactory.createTitledBorder("Imagen Original"));
        panelOriginal = new ImagePanel();
        p1.add(panelOriginal, BorderLayout.CENTER);

        JPanel p2 = new JPanel(new BorderLayout());
        p2.setBorder(BorderFactory.createTitledBorder("Imagen Procesada"));
        panelProcesado = new ImagePanel();
        p2.add(panelProcesado, BorderLayout.CENTER);

        centerPanel.add(p1);
        centerPanel.add(p2);
        add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Herramientas"));

        btnNegativo = new JButton("Negativo");
        btnEscalaGris = new JButton("Escala de grises");
        btnBrillo = new JButton("Brillo");
        btnEspejo = new JButton("Espejo");
        btnRotar = new JButton("Rotar");
        btnDeshacer = new JButton("Deshacer");
        btnReiniciar = new JButton("Reiniciar");

        btnNegativo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEscalaGris.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBrillo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEspejo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRotar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDeshacer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReiniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(btnNegativo);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(btnEscalaGris);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(btnBrillo);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(btnEspejo);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(btnRotar);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(btnDeshacer);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(btnReiniciar);
        rightPanel.add(Box.createVerticalStrut(15));

        historialArea = new JTextArea(10, 20);
        historialArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(historialArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Historial"));
        rightPanel.add(scroll);
        add(rightPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Estado: Sin cambios");
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setOriginalImage(BufferedImage img){
        panelOriginal.setImage(img);
    }
    public void setProcesadaImage(BufferedImage img){
        panelProcesado.setImage(img);
    }

    public void setStatus(String status){
        statusLabel.setText("Estado: " + status);
    }
    public void updateHistorialView(int ops, List<String> lista){
        StringBuilder sb = new StringBuilder();
        sb.append("Total Operaciones realizadas:\n").append(ops).append("\n");
        for (int i = 0; i < lista.size(); i++){
            sb.append(i+1).append(". ").append(lista.get(i)).append("\n");
            }
            historialArea.setText(sb.toString());
    }
    public void showErrorDialog(String msg){
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addLoadListener(ActionListener listener){
        btnCargar.addActionListener(listener);
    }
    public void addSaveListener(ActionListener listener){
        btnGuardar.addActionListener(listener);
    }
    public void addNegativoListener(ActionListener listener){
        btnNegativo.addActionListener(listener);
    }
    public void addGrayscaleListener(ActionListener listener){
        btnEscalaGris.addActionListener(listener);
    }
    public void addBrilloListener(ActionListener listener){
        btnBrillo.addActionListener(listener);
    }
    public void addEspejoListener(ActionListener listener){
        btnEspejo.addActionListener(listener);
    }
    public void addRotarListener(ActionListener listener){
        btnRotar.addActionListener(listener);
    }
    public void addDeshacerListener(ActionListener listener){
        btnDeshacer.addActionListener(listener);
    }
    public void addReiniciarListener(ActionListener listener){
        btnReiniciar.addActionListener(listener);
    }
}
