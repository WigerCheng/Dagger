package dagger;

import java.util.List;

public interface Command {

    /**
     * Process the rest of the command's words and do something.
     * 处理命令的字符并执行某些操作。
     *
     * @param input 命令
     * @return 命令执行状态
     */
    Status handleInput(List<String> input);

    /**
     * 命令状态
     * <p>
     * INVALID => 无效
     * HANDLED => 处理
     */
    enum Status {
        INVALID,
        HANDLED
    }
}
