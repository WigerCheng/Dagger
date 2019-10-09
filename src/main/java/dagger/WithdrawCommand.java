package dagger;

import javax.inject.Inject;
import java.math.BigDecimal;

final class WithdrawCommand extends BigDecimalCommand {

    private Outputter outputter;
    private Database.Account account;
    private BigDecimal minimumBalance, maximumWithdrawal;

    @Inject
    WithdrawCommand(Outputter outputter,
                    Database.Account account,
                    @MinimumBalance BigDecimal minimumBalance,
                    @MaximumWithdrawal BigDecimal maximumWithdrawal) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
        this.minimumBalance = minimumBalance;
        this.maximumWithdrawal = maximumWithdrawal;
    }

    @Override
    void handleAmount(BigDecimal amount) {
        if (amount.compareTo(maximumWithdrawal) > 0) {
            outputter.output(
                    String.format(
                            "you don't have sufficient funds to withdraw %s. "
                                    + "your balance is %s and the maximumWithdrawal balance is %s",
                            amount, account.balance(), maximumWithdrawal));
            return;
        }
        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.compareTo(minimumBalance) < 0) {
            outputter.output(
                    String.format(
                            "you don't have sufficient funds to withdraw %s. "
                                    + "your balance is %s and the minimum balance is %s",
                            amount, account.balance(), minimumBalance));
        } else {
            account.withdraw(amount);
            outputter.output("your new balance is: " + account.balance());
        }
    }
}
