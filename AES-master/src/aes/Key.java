/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;

/**
 *
 * @author kiro
 */
public class Key {

    private WordPoly[] keyExpansion;
    private int Nr;     //Number of rounds
    private int Nk;     //Number of words in cipher key
    private final int Nb = 4;

    /**
     * Expand the cipher key from a String. <br />
     *
     * 8 char means 128 bit key <br /> 12 char means 192 bit key <br /> 16 char
     * means 256 bit key <br />
     *
     * @param cipherKey
     */
    public Key(String cipherKey, boolean unicodeKey) {
        setRoundNumber(cipherKey.length());
        keyExpansion = new WordPoly[Nb * (Nr + 1)];
        if (unicodeKey) {
            for (int i = 0; i < Nk; i++) {
                WordPoly word = AesParser.charsToWord(
                        cipherKey.charAt(i * 2), cipherKey.charAt(i * 2 + 1));
                keyExpansion[i] = word;
            }
        } else {
            for (int i = 0; i < Nk; i++) {
                WordPoly word = new WordPoly();
                String str = cipherKey.substring(i * 8, (i + 1) * 8);
                word.x0 = new BinPoly(Integer.parseInt(str.substring(0, 2), 16));
                word.x1 = new BinPoly(Integer.parseInt(str.substring(2, 4), 16));
                word.x2 = new BinPoly(Integer.parseInt(str.substring(4, 6), 16));
                word.x3 = new BinPoly(Integer.parseInt(str.substring(6, 8), 16));
                keyExpansion[i] = word;
            }
        }
        expand();
    }

    private void expand() {
        for (int i = Nk; i < Nb * (Nr + 1); i++) {
            WordPoly temp = keyExpansion[i - 1];
            if (i % Nk == 0) {
                temp = rotWord(temp);
                temp = subWord(temp);
                temp = temp.addTo(RCon.getInstance().rcon.get(i / Nk - 1));
            } else if ((Nk == 8) && (i % 4 == 0)) {
                temp = subWord(temp);
            }
            temp = temp.addTo(keyExpansion[i - Nk]);
            keyExpansion[i] = temp;
        }
    }

    public int getNr() {
        return Nr;
    }

    /**
     * Get the key for specific round. Round 0 is the original cipher key.
     *
     * @param round
     * @return
     */
    public State getRoundKey(int round) {
        State result = new State();
        for (int j = 0; j < 4; j++) {
            result.wordToCollumn(keyExpansion[round * 4 + j], j);
        }
        return result;
    }

    private WordPoly rotWord(WordPoly word) {
        return word.multiply(new WordPoly(0, 0, 0, 1));
    }

    private WordPoly subWord(WordPoly word) {
        WordPoly result = new WordPoly();
        int x = (word.x0.poly & 0xf0) >> 4;
        int y = (word.x0.poly & 0x0f);
        result.x0.poly = SBox.getInstance().apply(x, y);
        x = (word.x1.poly & 0xf0) >> 4;
        y = (word.x1.poly & 0x0f);
        result.x1.poly = SBox.getInstance().apply(x, y);
        x = (word.x2.poly & 0xf0) >> 4;
        y = (word.x2.poly & 0x0f);
        result.x2.poly = SBox.getInstance().apply(x, y);
        x = (word.x3.poly & 0xf0) >> 4;
        y = (word.x3.poly & 0x0f);
        result.x3.poly = SBox.getInstance().apply(x, y);
        return result;
    }

    private void setRoundNumber(int cipherKeyLenght) {
        switch (cipherKeyLenght) {
            case 32:    //for hexadecimal key input
            case 8:     //for Unicode key input
                Nr = 10;
                Nk = 4;
                break;
            case 48:
            case 12:
                Nr = 12;
                Nk = 6;
                break;
            case 64:
            case 16:
                Nr = 14;
                Nk = 8;
                break;
        }
    }

    @Override
    public String toString() {
        String str = "Nr=" + Nr + ", Nk=" + Nk + ", Nb=" + Nb + "\n";
        for (int i = 0; i < keyExpansion.length; i++) {
            WordPoly wordPoly = keyExpansion[i];
            str += "w[" + i + "]= " + wordPoly + "\n";
        }
        return str;
    }
}
