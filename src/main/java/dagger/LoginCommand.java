package dagger;

import javax.inject.Inject;

final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;
    private final Database database;

    @Inject
    LoginCommand(Database database, Outputter outputter) {
        this.database = database;
        this.outputter = outputter;
        System.out.println("Creating a new " + this);
    }

    @Override
    protected Result handleArg(String username) {
        Database.Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance:" + account.balance());
        return Result.handled();
    }
}
