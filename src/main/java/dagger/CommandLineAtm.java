package dagger;

import java.util.Scanner;

public class CommandLineAtm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandProcessor commandProcessor =
                DaggerCommandProcessorFactory.create().processor();
        while (scanner.hasNextLine()) {
            commandProcessor.process(scanner.nextLine());
        }
    }

}
