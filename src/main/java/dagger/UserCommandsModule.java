package dagger;

import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
abstract class UserCommandsModule {
    @Binds
    @IntoMap
    @StringKey("deposit")
    abstract Command depositCommand(DepositCommand depositCommand);

    @Binds
    @IntoMap
    @StringKey("withdraw")
    abstract Command withdrawCommand(WithdrawCommand withdrawCommand);
}
