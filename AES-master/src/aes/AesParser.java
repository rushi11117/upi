/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;

/**
 *
 * @author kiro
 */
public class AesParser {

    /**
     * Parse message, transforms and splits it in AES State blocks of 128 bits.
     * Each char is a unicode character of 16-bit, so each block contains 8
     * characters. Adds padding if necessary.
     *
     * @param str
     * @return
     */
    public static State[] getStateBlocks(String str) {
        str = str.trim();
        if (str.length() % 8 != 0) {
            int remainder = (str.length() % 8);
            for (int i = 0; i < (8 - remainder); i++) {
                str += " ";
            }
        }
        State[] result = new State[str.length() / 8];
        for (int i = 0; i < str.length() / 8; i++) {
            result[i] = getStateFromText(str.substring(i * 8, (i + 1) * 8));
        }
        return result;
    }

    /**
     * Creates a State by parsing an 8 characters string. Each State contains 4
     * WordPoly (32 bit). Each two consecutive characters (16 bit unicode)
     * represents a word.
     *
     * @param str the string
     * @return
     */
    public static State getStateFromText(String str) {
        State result = new State();
        for (int j = 0; j < 4; j++) {
            result.wordToCollumn(charsToWord(str.charAt(j * 2), str.charAt(j * 2 + 1)), j);
        }
        return result;
    }

    /**
     * Creates a word (32 bit) from 2 characters (16 bit each)
     *
     * @param ch1
     * @param ch2
     * @return
     */
    public static WordPoly charsToWord(char ch1, char ch2) {
        int c1 = (int) ch1;
        int c2 = (int) ch2;
        return new WordPoly((c1 & 0xff00) >> 8,
                (c1 & 0x00ff),
                (c2 & 0xff00) >> 8,
                (c2 & 0x00ff));
    }

    /**
     * Creates a 2 character (16 bit each) array from a word (32 bit)
     *
     * @param word
     * @return
     */
    public static char[] wordToString(WordPoly word) {
        char[] result = {
            Character.toChars((word.x0.poly << 8) | word.x1.poly)[0],
            Character.toChars((word.x2.poly << 8) | word.x3.poly)[0]};
        return result;
    }

    /**
     * Creates a String representation (Unicode) from an array of States
     * @param states
     * @return 
     */
    public static String getStringFromState(State[] states) {
        String result = "";
        for (State state : states) {
            for (int i = 0; i < 4; i++) {
                char [] chr = wordToString(state.collumnAsWord(i));
                result += Character.toString(chr[0]) + Character.toString(chr[1]);
            }
        }
        return result.trim();
    }
}
