package bankAccountApp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ACHServiceImplTest {

    @Test
    public void registerAccount_returns_false_by_default() {
        ACHServiceImpl svc = new ACHServiceImpl();
        boolean result = svc.registerAccount(1, 2, 3, 4);
        assertEquals(false, result);
    }

    @Test
    public void transferAmount_returns_false_by_default() {
        ACHServiceImpl svc = new ACHServiceImpl();
        boolean result = svc.transferAmount(1, 2, 3, 4, 100f);
        assertEquals(false, result);
    }
}
