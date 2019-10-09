package dagger;

import javax.inject.Inject;
import java.math.BigDecimal;

final class WithdrawCommand extends BigDecimalCommand {

    private Outputter outputter;
    private Database.Account account;
    private BigDecimal minimumBalance;
    private WithdrawalLimiter withdrawalLimiter;

    @Inject
    WithdrawCommand(Outputter outputter,
                    Database.Account account,
                    @MinimumBalance BigDecimal minimumBalance,
                    @MaximumWithdrawal BigDecimal maximumWithdrawal,
                    WithdrawalLimiter withdrawalLimiter
    ) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
        this.minimumBalance = minimumBalance;
        this.withdrawalLimiter = withdrawalLimiter;
    }

    @Override
    void handleAmount(BigDecimal amount) {
        BigDecimal remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit();
        if (amount.compareTo(remainingWithdrawalLimit) > 0) {
            outputter.output(
                    String.format(
                            "you may not withdraw %s; you may withdraw %s more in this session",
                            amount, remainingWithdrawalLimit));
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
            withdrawalLimiter.recordWithdrawal(amount);
            outputter.output("your new balance is: " + account.balance());
        }
    }
}
