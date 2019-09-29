package dagger;

import javax.inject.Inject;

final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;

    @Inject
    LoginCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    protected Status handleArg(String username) {
        outputter.output(username + " is logged in.");
        return Status.HANDLED;
    }
}
