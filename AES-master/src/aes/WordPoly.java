package aes;

/**
 *
 * @author kiro
 */
public class WordPoly {

    BinPoly x3, x2, x1, x0;

    public WordPoly(int a0, int a1, int a2, int a3) {
        x3 = new BinPoly(a3);
        x2 = new BinPoly(a2);
        x1 = new BinPoly(a1);
        x0 = new BinPoly(a0);
    }
    
    public WordPoly() {
        x3 = new BinPoly(0);
        x2 = new BinPoly(0);
        x1 = new BinPoly(0);
        x0 = new BinPoly(0);
    }
    

    public WordPoly(BinPoly a0, BinPoly a1, BinPoly a2, BinPoly a3) {
        x3 = a3;
        x2 = a2;
        x1 = a1;
        x0 = a0;
    }

    public WordPoly addTo(WordPoly other) {
        return new WordPoly(this.x0.addTo(other.x0),
                this.x1.addTo(other.x1),
                this.x2.addTo(other.x2),
                this.x3.addTo(other.x3));

    }

    public WordPoly multiply(WordPoly other) {
        BinPoly d3 = this.x3.multiply(other.x0)
                .addTo(this.x2.multiply(other.x1))
                .addTo(this.x1.multiply(other.x2))
                .addTo(this.x0.multiply(other.x3));
        BinPoly d2 = this.x2.multiply(other.x0)
                .addTo(this.x1.multiply(other.x1))
                .addTo(this.x0.multiply(other.x2))
                .addTo(this.x3.multiply(other.x3));
        BinPoly d1 = this.x1.multiply(other.x0)
                .addTo(this.x0.multiply(other.x1))
                .addTo(this.x3.multiply(other.x2))
                .addTo(this.x2.multiply(other.x3));
        BinPoly d0 = this.x0.multiply(other.x0)
                .addTo(this.x3.multiply(other.x1))
                .addTo(this.x2.multiply(other.x2))
                .addTo(this.x1.multiply(other.x3));
        return new WordPoly(d0, d1, d2, d3);
    }

    public void debug() {
        System.out.println(x3.toString() + " " + x2.toString()
                + " " + x1.toString() + " " + x0.toString());
    }

    @Override
    public String toString() {
        return Integer.toHexString(x0.poly) + Integer.toHexString(x1.poly)
                + Integer.toHexString(x2.poly) + Integer.toHexString(x3.poly);
    }
}
