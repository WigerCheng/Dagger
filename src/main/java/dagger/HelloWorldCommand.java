package dagger;

import javax.inject.Inject;
import java.util.List;

final class HelloWorldCommand implements Command {

    private final Outputter outputter;

    @Inject
    public HelloWorldCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    public Status handleInput(List<String> input) {
        //输入 hello 输出 world!
        outputter.output("world!");
        return Status.HANDLED;
    }
}
