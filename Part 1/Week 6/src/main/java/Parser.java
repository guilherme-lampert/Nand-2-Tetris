
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private ArrayList<String> asmFile;
    private int currentCommandAddress;

    public Parser(String fileName) {
        this.asmFile = new ArrayList<>();
        this.currentCommandAddress = -1;

        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine().strip();
                if (command.startsWith("//") || command.equals("")) {
                    continue;
                }
                asmFile.add(command);
            }
        } catch (IOException e) {
            System.out.println("Error while reading the assembly file: " + e.getMessage());
        }
    }
    
    public int getCurrentCommandAddress() {
        return currentCommandAddress;
    }

    // remove the current command from the asm file. Only used when dealing with labels
    public void removeCurrentCommand() {
        asmFile.remove(currentCommandAddress);
        currentCommandAddress--;
    }
    
    // Checks if the asm file have more commands to be read
    public boolean hasMoreCommands() {
        if (currentCommandAddress < asmFile.size() - 1) {
            return true;
        }

        currentCommandAddress = -1;
        return false;
    }

    // Advances the current command address
    public void advance() {
        currentCommandAddress++;
    }

    // Returns the current command type
    public CommandType commandType() {
        if (asmFile.get(currentCommandAddress).startsWith("@")) {
            return CommandType.A_COMMAND;

        } else if (asmFile.get(currentCommandAddress).startsWith("(")) {
            return CommandType.L_COMMAND;

        }
        return CommandType.C_COMMAND;
    }

    // Returns the symbol of an address
    public String symbol() {
        String command = asmFile.get(currentCommandAddress);

        if (commandType() == CommandType.A_COMMAND) {
            return command.substring(1);
        }

        int lastChar = command.length() - 1;
        return command.substring(1, lastChar);

    }

    // Returns the mnemonic of a comp
    public String comp() {
        String command = asmFile.get(currentCommandAddress);

        if (command.contains("=")) {
            String[] commandParts = command.split("=");
            return commandParts[1];

        } else if (command.contains(";")) {
            String[] commandParts = command.split(";");
            return commandParts[0];
        }
        return "";
    }

    // Returns the mnemonic of a dest
    public String dest() {
        String command = asmFile.get(currentCommandAddress);

        if (command.contains("=")) {
            String[] commandParts = command.split("=");
            return commandParts[0];
        }
        return "";
    }

    // Returns the mnemonic of a jump
    public String jump() {
        String command = asmFile.get(currentCommandAddress);

        if (command.contains(";")) {
            String[] commandParts = command.split(";");
            return commandParts[1];
        }
        return "";
    }

}
