package dagger;

import javax.inject.Singleton;
import java.util.Scanner;

public class CommandLineAtm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandProcessor commandProcessor =
                DaggerCommandLineAtm_CommandProcessorFactory.create().processor();
        while (scanner.hasNextLine()) {
            commandProcessor.process(scanner.nextLine());
        }
    }

    @Singleton
    @Component(modules = {SystemOutModule.class, HelloWorldModule.class, UserCommandsModule.class, LoginCommandModule.class})
    interface CommandProcessorFactory {
        CommandProcessor processor();
    }
}
