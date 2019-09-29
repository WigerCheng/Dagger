package dagger;

import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
abstract class LoginCommandModule {
    /**
     * ` @StringKey注释，结合@IntoMap，告诉匕首如何填充 Map<String, Command>
     * 要利用这一点，我们可以将CommandRouter的构造函数参数切换为Map<String, Command>。
     * 请注意，Command它本身将不再起作用。
     */
    @Binds
    @IntoMap
    @StringKey("login")
    abstract Command loginCommand(LoginCommand loginCommand);
}
