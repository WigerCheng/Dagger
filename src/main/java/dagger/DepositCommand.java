package dagger;

import javax.inject.Inject;
import java.math.BigDecimal;

final class DepositCommand extends BigDecimalCommand {

    private final Database.Account account;
    private final Outputter outputter;

    @Inject
    DepositCommand(Database.Account account, Outputter outputter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
    }

    @Override
    void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        outputter.output(account.username() + " now has: " + account.balance());
    }
}
