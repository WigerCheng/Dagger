package dagger;

@PerSession
@Subcomponent(modules = {UserCommandsModule.class, AmountsModule.class})
public interface UserCommandsRouter {

    CommandRouter router();

    @Subcomponent.Factory
    interface Factory {
        UserCommandsRouter create(@BindsInstance Database.Account account);
    }

    @Module(subcomponents = UserCommandsRouter.class)
    interface InstallationModule {
    }
}
