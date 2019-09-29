package dagger;

import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

/**
 * 模块是绑定方法的集合，这些方法为Dagger提供了有关如何提供实例的说明。
 * 与直接在类的构造函数上使用的 @Inject不同， @Binds方法必须在模块内部。
 */
@Module
abstract class HelloWorldModule {

    /**
     * 绑定方法
     * <p>
     * This @Binds method tells Dagger that when something depends on a Command,
     * Dagger should provide a HelloWorldCommand object in its place.
     * Notice that the return type of the method, `Command`, is the type that Dagger now knows how to provide,
     * and the parameter type is the type that Dagger knows to use when something depends on Command.
     * <p>
     * 当某个地方需要 Command的时候，@Binds 方法会告诉 Dagger需要在请求的位置提供一个 HelloWorldCommand对象。
     * <p>
     * 该方法是抽象的，因为仅声明它就足以告诉Dagger该做什么。
     * Dagger实际上并未调用此方法或为其提供实现。
     *
     * @param helloWorldCommand 参数类型是Dagger知道如何提供的类型
     * @return 参数类型是Dagger知道如何使用的类型
     *
     * 为了告诉 Dagger在 HelloWorldModule中寻找该 @Binds方法，我们将其添加到 @Component批注中。
     * @see CommandRouterFactory
     */
    @Binds
    @IntoMap
    @StringKey("hello")
    abstract Command helloWorldCommand(HelloWorldCommand helloWorldCommand);
}
