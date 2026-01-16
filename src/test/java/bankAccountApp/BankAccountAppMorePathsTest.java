package bankAccountApp;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BankAccountAppMorePathsTest {

    @Test
    public void testMain_average_maximum_minimum_then_quit() {
        String input = "AVERAGE\nMAXIMUM\nminimum\nquit\n";
        InputStream originalIn = System.in;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            BankAccountApp.main(new String[]{});
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testMain_delete_nonexistent_then_quit() {
        String input = "delete\n1\nquit\n";
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
