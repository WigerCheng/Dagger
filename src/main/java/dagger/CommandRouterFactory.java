package dagger;

/**
 * 为了告诉Dagger在中寻找该 @Binds方法 HelloWorldModule，我们将其添加到 @Component注释中。
 */
@Component(modules = HelloWorldModule.class)
public interface CommandRouterFactory {
    CommandRouter router();
}
