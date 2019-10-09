package dagger;

import javax.inject.Singleton;

@Singleton
@Component(modules = {SystemOutModule.class, CommandsModule.class, UserCommandsRouter.InstallationModule.class})
interface CommandProcessorFactory {
    CommandProcessor processor();
}
