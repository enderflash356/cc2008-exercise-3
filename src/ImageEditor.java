public class ImageEditor {
    private Image og;

    public ImageEditor(Image og) {
        this.og = og;
    }

    
    public Image negative() {
        int h = og.getHeight();
        int w = og.getWidth();
        Image res = new Image(w, h);
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel p = og.getPixel(r, c);
                res.setPixel(r, c, new Pixel(255 - p.r, 255 - p.g, 255 - p.b));
            }
        }
        return res;
    }

    public Image grayscale() {
        int h = og.getHeight();
        int w = og.getWidth();
        Image res = new Image(w, h);
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel p = og.getPixel(r, c);
                int gray = (int) (0.299 * p.r + 0.587 * p.g + 0.114 * p.b);
                res.setPixel(r, c, new Pixel(gray, gray, gray));
            }
        }
        return res;
    }

    public Image brightness(int amount) {
        int h = og.getHeight();
        int w = og.getWidth();
        Image res = new Image(w, h);
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel p = og.getPixel(r, c);
                res.setPixel(r, c, new Pixel(p.r + amount, p.g + amount, p.b + amount));
            }
        }
        return res;
    }

    public Image mirrorHorizontal() {
        int h = og.getHeight();
        int w = og.getWidth();
        Image res = new Image(w, h);
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                res.setPixel(r,c, og.getPixel(r, w - c - 1));
            }
        }
        return res;
    }

    public Image rotate90() {
        int h = og.getHeight();
        int w = og.getWidth();
        Image res = new Image(w, h);
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                res.setPixel(c, h - r - 1, og.getPixel(r, c));
            }
        }
        return res;
    }
}