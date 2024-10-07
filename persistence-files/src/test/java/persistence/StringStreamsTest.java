
package persistence;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class StringStreamsTest {
    static final String PRINT_STREAM_FILE = "printStreamFile.txt";
    static final String PRINT_WRITER_FILE = "printWriterFile.txt";
    static final String DIRECTORY = "C:\\TNx64\\Tcimg";
    static final String OUTPUT_PATH_FILE = "TreeFile.txt";
    @Test
    @Disabled
    void printStreamTest() throws Exception{
        PrintStream printStream = new PrintStream(PRINT_STREAM_FILE);
        printStream.println("HELLO");
        printStream.close();
    }
    @Test
    @Disabled
    void printWriterTest() throws Exception{
        PrintWriter printWriter = new PrintWriter(PRINT_WRITER_FILE);
        printWriter.println("HELLO");
        printWriter.close();
    }
    @Test
    @Disabled
    void bufferedReaderTest() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(PRINT_WRITER_FILE));
        assertEquals("HELLO",reader.readLine());
        reader.close();
    }
    @Test
    void printingDirectoryTest() throws Exception {
        printDirectoryContent(DIRECTORY,3);
    }
    /**
     * 
     * @param path -  path to a directory
     * @param depth -  number of been walked levels
     */
    void printDirectoryContent(String path, int depth) throws IOException {
        Path startDirectory = Paths.get(path);

        try (PrintWriter printWriter = new PrintWriter(OUTPUT_PATH_FILE)) {
            Files.walkFileTree(startDirectory, new SimpleFileVisitor<>() {
                private int currentDepth = 0;

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (currentDepth <= depth) {
                        printPathIntoFile(printWriter, dir);
                        currentDepth++;
                    }
                    return FileVisitResult.CONTINUE;

                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (currentDepth <= depth) {
                        printPathIntoFile(printWriter, file);
                    }
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    currentDepth--;
                    return FileVisitResult.CONTINUE;
                }

                private void printPathIntoFile(PrintWriter printWriter, Path path) {
                    printWriter.println("  ".repeat(currentDepth * 2) + path.getFileName());
                }
            });
        }

        //TODO
        //dir1
          //dir11
            //file
            //dir111
          //dir12
        //Consider class Path
        //Consider class Files
        //Consider method https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Files.html#walkFileTree(java.nio.file.Path,java.util.Set,int,java.nio.file.FileVisitor)
    }

}
