
public class Pixel {
    public int r;
    public int g;
    public int b;

    public Pixel(int r, int g, int b) {
        this.r = Math.min(255, Math.max(0,r));
        this.g = Math.min(255, Math.max(0,g));
        this.b = Math.min(255, Math.max(0,r));
    }
}
