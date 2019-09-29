package dagger;

@Module
abstract class SystemOutModule {
    /**
     * A @Provides method works a lot like an @Inject constructor: here it tells Dagger that when it needs an instance of Outputter,
     * it should call SystemOutModule.textOutputter() to get one.
     * <p>
     * `@Provides 和 @Inject构造函数 工作原理很像， 某个地方需要Outputter的时候，就会告诉Dagger去请求SystemOutModule.textOutputter()获得一个Outputter
     * <p>
     * `@Provides方法是模块中的具体方法，该模块告诉Dagger，当某些对象请求该类型的实例返回时，该方法应调用该方法以获取实例。
     * 像@Inject 构造函数一样，它们可以具有参数：这些参数是它们的依赖项。
     * <p>
     * `@Provides方法可以包含任意代码，只要它们返回提供的类型的实例即可。他们不需要在每次调用时都创建一个新实例。
     */
    @Provides
    static Outputter textOutputter() {
        //编写一个static仅创建并返回Outputter自身实例的方法，而不是做所有这些事情！
        return System.out::println;
    }
}
