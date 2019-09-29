package dagger;

import javax.inject.Inject;
import java.util.List;

final class HelloWorldCommand implements Command {

    @Inject
    public HelloWorldCommand() {
    }

    public String key() {
        return "hello";
    }


    public Status handleInput(List<String> input) {
        if (!input.isEmpty()) {
            return Status.INVALID;
        }
        //输入 hello 输出 world!
        System.out.println("world!");
        return Status.HANDLED;
    }
}
