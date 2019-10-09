package dagger;

@PerSession
@Subcomponent(modules = {UserCommandsModule.class, AmountsModule.class, AccountModule.class})
public interface UserCommandsRouter {

    CommandRouter router();

    @Subcomponent.Factory
    interface Factory {
        UserCommandsRouter create(@BindsInstance @Username String username);
    }

    @Module(subcomponents = UserCommandsRouter.class)
    interface InstallationModule {
    }
}
