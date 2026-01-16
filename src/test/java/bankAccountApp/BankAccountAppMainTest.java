package bankAccountApp;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BankAccountAppMainTest {

    @Test
    public void testMain_quitImmediately() {
        String input = "quit\n";
        InputStream originalIn = System.in;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            BankAccountApp.main(new String[]{});
        } finally {
            System.setIn(originalIn);
        }
    }
}
