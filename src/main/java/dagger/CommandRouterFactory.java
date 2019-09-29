package dagger;

/**
 * 为了告诉Dagger在中寻找该 @Binds方法 HelloWorldModule, SystemOutModule，我们将其添加到 @Component注释中。
 * <p>
 * HelloWorldModule==>LoginCommandModule
 * 这开始显示使用Dagger的一些好处。
 * 通过一行 声明式更改，我们可以更改Command收到的内容 CommandRouter。CommandRouter没有任何变化就能继续运行。
 * 使用这种方法，您可以编写许多不同版本的应用程序并重复使用代码，而无需进行大量更改。
 */
@Component(modules = {LoginCommandModule.class, SystemOutModule.class})
public interface CommandRouterFactory {
    CommandRouter router();
}
