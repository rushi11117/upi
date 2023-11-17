package aes;

/**
 * The type Word poly.
 *
 * @author kiro
 */
public class WordPoly {

    /**
     * The X 3.
     */
    BinPoly x3, /**
     * The X 2.
     */
    x2, /**
     * The X 1.
     */
    x1, /**
     * The X 0.
     */
    x0;

    /**
     * Instantiates a new Word poly.
     *
     * @param a0 the a 0
     * @param a1 the a 1
     * @param a2 the a 2
     * @param a3 the a 3
     */
    public WordPoly(int a0, int a1, int a2, int a3) {
        x3 = new BinPoly(a3);
        x2 = new BinPoly(a2);
        x1 = new BinPoly(a1);
        x0 = new BinPoly(a0);
    }

    /**
     * Instantiates a new Word poly.
     */
    public WordPoly() {
        x3 = new BinPoly(0);
        x2 = new BinPoly(0);
        x1 = new BinPoly(0);
        x0 = new BinPoly(0);
    }


    /**
     * Instantiates a new Word poly.
     *
     * @param a0 the a 0
     * @param a1 the a 1
     * @param a2 the a 2
     * @param a3 the a 3
     */
    public WordPoly(BinPoly a0, BinPoly a1, BinPoly a2, BinPoly a3) {
        x3 = a3;
        x2 = a2;
        x1 = a1;
        x0 = a0;
    }

    /**
     * Add to word poly.
     *
     * @param other the other
     *
     * @return the word poly
     */
    public WordPoly addTo(WordPoly other) {
        return new WordPoly(this.x0.addTo(other.x0),
                this.x1.addTo(other.x1),
                this.x2.addTo(other.x2),
                this.x3.addTo(other.x3));

    }

    /**
     * Multiply word poly.
     *
     * @param other the other
     *
     * @return the word poly
     */
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

    /**
     * Debug.
     */
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
