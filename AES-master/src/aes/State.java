/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;

/**
 * The type State.
 *
 * @author kiro
 */
public class State {
    /**
     * The State.
     */
    public int [] state = new int[16];

    /**
     * Instantiates a new State.
     *
     * @param state the state
     */
    public State(int [] state){
        this.state = state;
    }

    /**
     * Instantiates a new State.
     */
    public State(){
        
    }

    /**
     * Collumn as word word poly.
     *
     * @param x the x
     *
     * @return the word poly
     */
    public WordPoly collumnAsWord(int x){
        return new WordPoly(state[x],state[4+x],state[8+x], state[12+x]);
    }

    /**
     * Word to collumn.
     *
     * @param w the w
     * @param x the x
     */
    public void wordToCollumn(WordPoly w, int x){
        state[x] = w.x0.poly;
        state[4+x] = w.x1.poly;
        state[8+x] = w.x2.poly;
        state[12+x] = w.x3.poly;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append("{");
                sb.append(Integer.toHexString(state[i*4+j]));
                sb.append("} ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
}
