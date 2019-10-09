package dagger;

/**
 * 为了告诉Dagger在中寻找该 @Binds方法 LoginCommandModule, UserCommandsModule, HelloWorldModule, SystemOutModule，我们将其添加到 @Component注释中。
 * <p>
 * 如果我们直接尝试把 HelloWorldModule.class, LoginCommandModule.class 加在一起，Dagger将会报告错误。
 * 这两个模块会发生冲突，它们各自告诉Dagger如何创建单个Command，而Dagger不知道哪个应该赢。
 * <p>
 * 我们希望CommandRouter依赖多个命令而不是一个。因为我们CommandRouter想要一个命令映射，
 * 所以我们将使用我们的@IntoMap每个Command应用程序映射到命令的前缀。
 *
 * @see LoginCommandModule
 */
//@Singleton
@Component(modules = {LoginCommandModule.class, UserCommandsModule.class, HelloWorldModule.class, SystemOutModule.class})
public interface CommandRouterFactory {
    CommandRouter router();
}
