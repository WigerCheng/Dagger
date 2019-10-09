package dagger;

import javax.inject.Singleton;

@Singleton
@Component(modules = {SystemOutModule.class, CommandsModule.class, UserCommandsRouter.InstallationModule.class, AmountsModule.class})
interface CommandProcessorFactory {
    CommandProcessor processor();
}
