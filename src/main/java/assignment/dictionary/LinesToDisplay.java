package assignment.dictionary;

import java.util.Iterator;


/**
 * A class that will be used to display the lines of text that are corrected.
 *
 */

public class LinesToDisplay {

    public static final int LINES = 20;     // Display 20 lines
    private AList<Wordlet>[] lines;
    private int currentLine;

    private static final int MAX_Char_count = 85;

    private int currentCharCount = 0;

    /**
     * Constructor for objects of class LinesToDisplay
     */
    public LinesToDisplay() {

    lines = (AList<Wordlet>[]) new AList[LINES];

    for(int i = 0; i < LINES; i++){
        lines[i] = new AList<>();
    }
    currentLine = 0;

    }

    /**
     * Add a new wordlet to the current line.
     *
     */
    public void addWordlet(Wordlet w) {
        //ADD CODE HERE TO ADD A WORDLET TO THE CURRENT LINE

        int wordCharCount = w.getWord().length() + (currentCharCount> 0 ? 1 : 0);

        //This if statement checks current line is higher than the limit
        //It also checks if the character count and word count can fit within the current line
        //if either of these conditions is false the line count is incremented
        if (currentLine < LINES && (currentCharCount + wordCharCount) < MAX_Char_count) {
            lines[currentLine].add(w);
            currentCharCount += wordCharCount;
        } else {

            nextLine();
            wordCharCount = w.getWord().length();
            lines[currentLine].add(w);
            currentCharCount = wordCharCount;
        }

    }

    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift
     * them all up by one if
     *
     */

    //This function increments the line count if current line count exceeds the limit
    public void nextLine() {
        currentLine++;
        if (currentLine >= lines.length) {
            // Manually shifting the lines up by one.
            for (int i = 0; i < LINES - 1; i++) {
                lines[i] = lines[i + 1];
            }
            // Create a new list for the last line.
            lines[LINES - 1] = new AList<>();

            currentLine = LINES - 1;
        }
        // Reset character count for the new line
        currentCharCount = 0;
    }

    public int getCurrentLine(){
        return currentLine;
    }

    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
