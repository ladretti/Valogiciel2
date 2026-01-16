package bankAccountApp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BankAccountEdgeCasesTest {
    Person accountHolder;
    Bank bank;
    BankAccount bankAccount;

    @Before
    public void setup() throws Exception {
        accountHolder = new Person("John", 'm', 22, 190, 172, "brown", "green", "jufm@gmail.com");
        bank = new Bank();
        bankAccount = new BankAccount(5000, 700, "05/21/2019", accountHolder);
    }

    @Test
    public void deposit_negative_amount_no_change() {
        bank.addAccount(bankAccount, 0);
        double before = bankAccount.getBalance();
        bankAccount.depositMoney(-100);
        assertEquals(before, bankAccount.getBalance(), 0.0);
    }

    @Test
    public void withdraw_exceeds_balance_or_limit_fails_and_keeps_balance() {
        bank.addAccount(bankAccount, 0);
        // withdraw far more than balance
        boolean r1 = bankAccount.withdrawMoney(10_000_000);
        assertEquals(false, r1);
        assertEquals(5000, bankAccount.getBalance(), 0.0);

        // withdraw that exceeds single-withdraw limit
        boolean r2 = bankAccount.withdrawMoney(800);
        assertEquals(false, r2);
        assertEquals(5000, bankAccount.getBalance(), 0.0);
    }
}
