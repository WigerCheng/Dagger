package dagger;

/**
 * 现在，HelloWorldCommand用于System.out.println()写入其输出。本
 * 着依赖注入的精神，让我们使用抽象，以便我们可以删除对的直接使用System.out。
 * 我们要做的是创建一个 Outputter类型，该类型对写入的文本执行某些操作。
 * 我们的默认实现仍可以使用System.out.println()，但这使我们可以在以后更改而不更改的灵活性HelloWorldCommand。
 * 例如，我们的测试可能使用将字符串添加到中的实现， List<String>以便我们可以检查输出了什么。
 *
 * @see SystemOutModule
 */
public interface Outputter {

    void output(String output);

}
