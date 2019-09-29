package dagger;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class CommandRouter {

    private final Map<String, Command> commands = Collections.emptyMap();

    /**
     * 用@Inject给函数做注释，告诉Dagger如何创建CommandRouter
     * 当我们需要一个CommandRouter时，Dagger调用new Commander()
     */
    @Inject
    public CommandRouter() {
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
