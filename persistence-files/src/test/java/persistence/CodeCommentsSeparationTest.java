package persistence;
import org.junit.jupiter.api.Test;
public class CodeCommentsSeparationTest {
        @Test
        // @Deprecated
        void separateTest() {
            CodeCommentsSeparation.main(new String[] {
                "test.txt",
                "code.txt",
                "comments.txt"
            });
        }
    }

