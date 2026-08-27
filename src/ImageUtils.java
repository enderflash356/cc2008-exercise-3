import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    public static Image load(String path)throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        if (img == null) {
            throw new IOException("Could not load image: " + path);
        }
        int w = img.getWidth();
        int h = img.getHeight();
        Image res = new Image(w, h);
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                int rgb = img.getRGB(c,r);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                res.setPixel(r, c, new Pixel(red, green, blue));
            }
        }
        return res;
    }

    public static void save(Image image, String path)throws IOException {
        BufferedImage img = toBufferedImage(image);
        String ext = path.substring(path.lastIndexOf(".") + 1);
        ImageIO.write(img, ext, new File(path));
    }

    public static BufferedImage toBufferedImage(Image image) {
        int h = image.getHeight();
        int w = image.getWidth();
        BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel p = image.getPixel(r, c);
                int rgb = (p.r << 16) | (p.g << 8) | p.b;
                res.setRGB(c,r, rgb);
            }
        }
        return res;
    }
}