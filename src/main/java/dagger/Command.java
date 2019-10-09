package dagger;

import java.util.List;
import java.util.Optional;

public interface Command {

    /**
     * Process the rest of the command's words and do something.
     * 处理命令的字符并执行某些操作。
     *
     * @param input 命令
     * @return 命令执行状态
     */
    Result handleInput(List<String> input);


    class Result {
        private final Status status;
        private final Optional<CommandRouter> nestedCommandRouter;

        Result(Status status, Optional<CommandRouter> nestedCommandRouter) {
            this.status = status;
            this.nestedCommandRouter = nestedCommandRouter;
        }

        static Result invalid() {
            return new Result(Status.INVALID, Optional.empty());
        }

        static Result handled() {
            return new Result(Status.HANDLED, Optional.empty());
        }

        static Result inputCompleted() {
            return new Result(Status.INPUT_COMPLETED, Optional.empty());
        }

        Status status() {
            return status;
        }

        static Result enterNestedCommandSet(CommandRouter nestedCommandRouter) {
            return new Result(Status.HANDLED, Optional.of(nestedCommandRouter));
        }

        Optional<CommandRouter> nestedCommandRouter() {
            return nestedCommandRouter;
        }

    }

    /**
     * 命令状态
     * <p>
     * INVALID => 无效
     * HANDLED => 处理
     * INPUT_COMPLETED => 输入完成
     */
    enum Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }
}
