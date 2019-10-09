package dagger;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

final class DepositCommand implements Command {

    private final Database database;
    private final Outputter outputter;

    @Inject
    public DepositCommand(Database database, Outputter outputter) {
        this.database = database;
        this.outputter = outputter;
        System.out.println("Creating a new " + this);
    }

    @Override
    public Result handleInput(List<String> input) {
        if (input.size() != 2) {
            return Result.invalid();
        }
        Database.Account account = database.getAccount(input.get(0));
        account.deposit(new BigDecimal(input.get(1)));
        outputter.output(account.username() + " now has: " + account.balance());
        return Result.handled();
    }
}
