
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        if (invalidArgs(args)) {
            System.out.println("Invalid file name");
            System.exit(1);
        }

        String hackFileName = getHackFileName(args[0]);
        String asmFileName = args[0];
        
        Parser parser = new Parser(asmFileName);
        SymbolTable symbolTable = new SymbolTable();

        firstRun(parser, symbolTable);
        ArrayList<String> hackFile = secondRun(parser, symbolTable);

        writeToHackFile(hackFile, hackFileName);
        System.out.println("Process completed");
    }

    // Executes the first run, checking the labels
    public static void firstRun(Parser parser, SymbolTable symbolTable) {

        while (parser.hasMoreCommands()) {
            parser.advance();

            if (!(parser.commandType() == CommandType.L_COMMAND)) {
                continue;
            }

            String symbol = parser.symbol();
            if (symbolTable.contains(symbol)) {
                continue;
            }

            int address = parser.getCurrentCommandAddress();
            symbolTable.addEntry(symbol, address);
            parser.removeCurrentCommand();
        }
    }

    // Executes the second run. It also returns the ArrayList with the hack file binary codes
    public static ArrayList<String> secondRun(Parser parser, SymbolTable symbolTable) {
        Code code = new Code();
        ArrayList<String> hackFile = new ArrayList<>();

        while (parser.hasMoreCommands()) {
            parser.advance();

            StringBuilder command = new StringBuilder();

            if (parser.commandType() == CommandType.C_COMMAND) {
                command.append("111");
                String comp = parser.comp();
                String dest = parser.dest();
                String jump = parser.jump();
                command.append(code.comp(comp));
                command.append(code.dest(dest));
                command.append(code.jump(jump));

            } else if (parser.commandType() == CommandType.A_COMMAND) {
                command.append("0");

                String symbol = parser.symbol();
                if (!symbolTable.contains(symbol) && !Character.isDigit(symbol.charAt(0))) {
                    symbolTable.addEntry(symbol);
                }

                Integer address = symbolTable.getAddress(symbol);
                if (address != null) {
                    command.append(code.symbol(address));
                } else {
                    address = Integer.valueOf(symbol);
                    command.append(code.symbol(address));
                }
            }

            hackFile.add(command.toString());
        }

        return hackFile;
    }

    // Returns true if args are invalid
    public static boolean invalidArgs(String[] args) {
        if (args.length != 1) {
            return true;
        }

        String[] stringParts = args[0].split("\\.");
        String extension = stringParts[1];

        if (!extension.equals("asm")) {
            return true;
        }

        return false;
    }

    // Returns the hack file name. Ex: file.asm -> file.hack
    public static String getHackFileName(String string) {
        String[] stringParts = string.split("\\.");
        String hackFileName = stringParts[0] + ".hack";
        return hackFileName;
    }

    // Writes the commands to the hack file
    public static void writeToHackFile(ArrayList<String> hackFile, String hackFileName) {
        try (FileWriter hackFileWriter = new FileWriter(hackFileName)) {
            for (String command : hackFile) {
                hackFileWriter.append(command + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error while writing to the hack file: " + e.getMessage());
        }
    }

}
