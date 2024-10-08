package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeCommentsSeparation {
    
    public static void main(String[] args) {    
        if (args.length != 3) {
            new IllegalArgumentException();
        }

        try {
            separateCodeComments(args[0], args[1], args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void separateCodeComments(String filePath, String codePath, String commentsPath) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        PrintWriter code = new PrintWriter(codePath);
        PrintWriter comments = new PrintWriter(commentsPath);

        fileReader.lines().forEach(line -> {
            if (isComment(line)) {
                comments.println(line);
            } else {
                code.println(line);
            }
        });

        code.close();
        comments.close();
        fileReader.close();
    }

    private static boolean isComment(String line) {
        return line.trim().startsWith("//");
    }
}
        //TODO - data from args[0] split to two files: args[1], args[2]
        //for sake of simplicity comments may be only on one line, like comments at this file
        // /* */ cannot be
        // code ...// comment .... cannot be
             //However // may be not only at beginning of line, like this
        //args[0] - path to file containing code and comments 
        //args[1] - path to file for placing only code
        //args[2] - path to file for placing only comments
        
  