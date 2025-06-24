package highlighting;

import java.util.List;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.awt.Color;

/** Configure the lexer and start the demo. */
public class Main {
    /**
     * Launch the demo.
     *
     * @param args arguments for the program (not used)
     */
    public static void main(String... args) {
        String defaultText =
                """
package controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* ApplicationListener that delegates to the MainGameController. Just some setup. */
public class LibgdxSetup extends Game {
    private final MainController mc;

    /**
     * The batch is necessary to draw ALL the stuff. Every object that uses draw need to know the
     * batch.
     */
    private SpriteBatch batch;
    // This batch is used to //draw the HUD /*elements*/ on it.\040
    private SpriteBatch hudBatch;

    /**
     * "ApplicationListener" that delegates to the "MainGameController". Just some setup.
     */
    public LibgdxSetup(MainController mc) {
        this.mc = mc;
    }

    @Over-ride 'someText'
    public void create() {
        // new ...
        char ch = new Character('a');
        return null;
    }
}
""";

        LexerUI.show(defaultText, Lexer.of(setupTokens()));
    }

    /**
     * TODO: Homework! Define the patterns for the individual tokens here (see comments).
     *
     * @return list of tokens for the syntax parts to be highlighted
     * 
     */
    private static List<Token> setupTokens() {
        return Arrays.asList(
                // Strings
                Token.of(
                    Pattern.compile("\"(\\.|[^\\\"])*\""),
                    Color.ORANGE
                ),
                // Einzelne Zeichen
                Token.of(
                    Pattern.compile("'(\\.|[^\\'])'"),
                    Color.RED
                ),
                // KeyWords
                Token.of(
                    Pattern.compile("\\b(package|import|class|public|private|final|return|null|new)\\b"),
                    Color.BLUE
                ),
                // Annotation
                Token.of(
                    Pattern.compile("@\\w+"),
                    Color.GRAY
                ), 
                // Einzeiliger Kommentar
                Token.of(
                    Pattern.compile("\\/\\/.*$", Pattern.MULTILINE),
                    Color.CYAN
                ),
                // Java-Doc
                Token.of(
                    Pattern.compile("\\/\\*\\*[\\s\\S]*?\\*\\/"),
                    Color.PINK
                ),
                // Mehrzeilige Kommentare
                Token.of(
                    Pattern.compile("\\/\\*(?!\\*)[\\s\\S]*?\\*\\/"),
                    Color.GREEN
                )
                
            );
    }
}
