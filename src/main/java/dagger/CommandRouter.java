package dagger;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

final class CommandRouter {

    private final Map<String, Command> commands;

    /**
     * 用@Inject给函数做注释，告诉Dagger如何创建CommandRouter
     * 当我们需要一个CommandRouter时，Dagger调用new Commander()
     * 现在CommandRouter，为该命令的构造函数添加一个参数。
     * 把原来的HelloWorldCommand 换成通用的Command
     * <p>
     * 因为Command是一个接口，没有@Inject注解的构造函数，
     * 所以Dagger并不知道怎么获取Command的实例，
     * 因此我们需要给Dagger更多的信息。
     *
     * @see HelloWorldModule
     */
    @Inject
    public CommandRouter(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * 根据输入中的第一个单词将输入字符串路由到它们。
     * 我们将为它提供一个空的命令映射。
     *
     * @param input 命令字符串
     * @return 命令状态
     */
    Command.Status route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }
        Command.Status status =
                command.handleInput(splitInput.subList(
                        1, splitInput.size()
                ));
        if (status == Command.Status.INVALID) {
            System.out.println(commandKey + ": invalid arguments");
        }
        return status;
    }


    /**
     * 无效命令
     *
     * @param input 命令字符串
     * @return Command.Status.INVALID
     */
    private Command.Status invalidCommand(String input) {
        System.out.println(
                String.format("couldn't understand \"%s\". please try again.", input)
        );
        return Command.Status.INVALID;
    }

    /**
     * 对输入的命令字符串分裂
     *
     * @param input 命令字符串
     * @return “Hello world” ==> {"hello","world"}
     */
    private List<String> split(String input) {
        return Arrays.asList(input.trim().split("\\s+"));
    }
}
