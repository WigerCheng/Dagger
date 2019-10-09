package dagger;

import javax.inject.Inject;
import java.util.Optional;

final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;
    private final UserCommandsRouter.Factory userCommandsRouterFactory;
    private final Optional<Database.Account> account;

    @Inject
    LoginCommand(Outputter outputter,
                 UserCommandsRouter.Factory userCommandsRouterFactory,
                 Optional<Database.Account> account) {
        this.outputter = outputter;
        this.userCommandsRouterFactory = userCommandsRouterFactory;
        this.account = account;
    }

    @Override
    protected Result handleArg(String username) {
        if (account.isPresent()) {
            String loggedInUser = account.get().username();
            outputter.output(loggedInUser + " is already logged in");
            if (!loggedInUser.equals(username)) {
                outputter.output("run `logout` first before trying to log in another user");
            }
            return Result.handled();
        } else {
            UserCommandsRouter userCommands = userCommandsRouterFactory.create(username);
            outputter.output(username + " is logged");
            return Result.enterNestedCommandSet(
                    userCommands.router()
            );
        }
    }
}
