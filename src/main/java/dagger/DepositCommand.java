package dagger;

import javax.inject.Inject;
import java.math.BigDecimal;

final class DepositCommand extends BigDecimalCommand {

    private final Database.Account account;
    private final Outputter outputter;
    private WithdrawalLimiter withdrawalLimiter;

    @Inject
    DepositCommand(
            Database.Account account,
            Outputter outputter,
            WithdrawalLimiter withdrawalLimiter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
        this.withdrawalLimiter = withdrawalLimiter;
    }

    @Override
    void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        withdrawalLimiter.recordDeposit(amount);
        outputter.output(account.username() + " now has: " + account.balance());
    }
}
