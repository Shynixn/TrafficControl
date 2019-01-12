import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleUnitTest {

    /**
     * Sample test.
     */
    @Test
    public void test() {
        int i = 5;
        int j = 6;

        int result = i + j;

        Assertions.assertEquals(11, result);
    }
}
