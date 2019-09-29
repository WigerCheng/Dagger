package dagger;

@Module
abstract class LoginCommandModule {
    @Binds
    abstract Command loginCommand(LoginCommand loginCommand);
}
