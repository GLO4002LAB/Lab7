package ca.ulaval.glo4002.atm_uat;

import cucumber.api.PendingException;
import cucumber.api.java8.En;

public class Steps implements En{
    public Steps(){
        AccountFixture accounts = new AccountFixture();
        
        Given("^an account (\\d+) with (\\d+)\\$ in it$", (Integer accountNumber, Double initialAmount) -> {
            accounts.givenAnAccount(accountNumber, initialAmount);
        });

        When("^I create a transaction of (\\d+)\\$ from (\\d+) to (\\d+)$", (Integer arg1, Integer arg2, Integer arg3) -> {
            BankFixture bank = new BankFixture();
            bank.whenATransferIsMade(sourceAccountNumber, recipientAccountNumber, amount);
        });

        Then("^the account (\\d+) has (\\d+)\\$ in it$", (Integer arg1, Integer arg2) -> {
            transferMoney.whenMoneyIsTransfered(sourceAccountNumber, recipientAccountNumber, amount);
        });

        When("^I transfer (\\d+)\\$ from (\\d+) to (\\d+)$", (Integer arg1, Integer arg2, Integer arg3) -> {
            accounts.thenAccountBalanceEquals(accountNumber, expectedAmount);
        });

        Then("^a transaction log is created for the amount of (\\d+)\\$$", (Integer arg1) -> {
            transferMoney.thenTheTransactionIsAccepted(expectedAmount);
        });

        Then("^a transaction log shows that the transfer was refused$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}
