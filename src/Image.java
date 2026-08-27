
public class Image {
    private Pixel[][] pixels;

    public Image(int height, int width) {
        this.pixels = new Pixel[height][width];
    }

    public int getHeight() {
        return pixels.length;
    }

    public int getWidth() {
        return pixels[0].length;
    }

    public Pixel getPixel(int row, int col) {
        return pixels[row][col];
    }

    public void setPixel(int row, int col, Pixel pixel) {
        this.pixels[row][col] = pixel;
    }
}
