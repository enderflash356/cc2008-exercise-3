import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {
  private BufferedImage image;

  public ImagePanel(){
    setPreferredSize(new Dimension(350, 350));
  }

  public void setImage(BufferedImage image){
    this.image = image;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    if(image != null){
      int panelW = getWidth();
      int panelH = getHeight();
      int imgW = image.getWidth();
      int imgH = image.getHeight();
      double scale = Math.min((double)panelW/imgW, (double)panelH/imgH);
      int newW = (int)(imgW*scale);
      int newH = (int)(imgH*scale);
      int x = (panelW - newW)/2;
      int y = (panelH - newH)/2;
      g.drawImage(image, x, y, newW, newH, this);
    }
    }
  

  
}
