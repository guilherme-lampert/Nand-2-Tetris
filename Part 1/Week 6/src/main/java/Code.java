
import java.util.HashMap;

public class Code {

    private HashMap<String, String> compCodes;
    private HashMap<String, String> destCodes;
    private HashMap<String, String> jumpCodes;

    public Code() {
        compCodes = getCompCodes();
        destCodes = getDestCodes();
        jumpCodes = getJumpCodes();
    }

    // Returns a hash map with the possible comp codes
    private HashMap<String, String> getCompCodes() {
        HashMap<String, String> codes = new HashMap<>();

        codes.put("0", "0101010");
        codes.put("1", "0111111");
        codes.put("-1", "0111010");
        codes.put("D", "0001100");
        codes.put("A", "0110000");
        codes.put("!D", "0001101");
        codes.put("!A", "0110001");
        codes.put("-D", "0001111");
        codes.put("-A", "0110011");
        codes.put("D+1", "0011111");
        codes.put("A+1", "0110111");
        codes.put("D-1", "0001110");
        codes.put("A-1", "0110010");
        codes.put("D+A", "0000010");
        codes.put("D-A", "0010011");
        codes.put("A-D", "0000111");
        codes.put("D&A", "0000000");
        codes.put("D|A", "0010101");
        codes.put("M", "1110000");
        codes.put("!M", "1110001");
        codes.put("-M", "1110011");
        codes.put("M+1", "1110111");
        codes.put("M-1", "1110010");
        codes.put("D+M", "1000010");
        codes.put("D-M", "1010011");
        codes.put("M-D", "1000111");
        codes.put("D&M", "1000000");
        codes.put("D|M", "1010101");

        return codes;
    }

    // Returns a hash map with the possible dest codes
    private HashMap<String, String> getDestCodes() {
        HashMap<String, String> codes = new HashMap<>();

        codes.put("", "000");
        codes.put("M", "001");
        codes.put("D", "010");
        codes.put("MD", "011");
        codes.put("A", "100");
        codes.put("AM", "101");
        codes.put("AD", "110");
        codes.put("AMD", "111");

        return codes;
    }

    // Returns a hash map with the possible jump codes
    private HashMap<String, String> getJumpCodes() {
        HashMap<String, String> codes = new HashMap<>();

        codes.put("", "000");
        codes.put("JGT", "001");
        codes.put("JEQ", "010");
        codes.put("JGE", "011");
        codes.put("JLT", "100");
        codes.put("JNE", "101");
        codes.put("JLE", "110");
        codes.put("JMP", "111");

        return codes;
    }

    // Returns the binary code of a dest mnemonic
    public String dest(String mnemonic) {
        return destCodes.get(mnemonic);
    }
    
    // Returns the binary code of a comp mnemonic
    public String comp(String mnemonic) {
        return compCodes.get(mnemonic);
    }
    
    // Returns the binary code of a jump mnemonic
    public String jump(String mnemonic) {
        return jumpCodes.get(mnemonic);
    }
    
    // Returns the binary code of an address
    public String symbol(int address) {
        String binaryCommand = Integer.toBinaryString(address);
        return String.format("%15s", binaryCommand).replace(" ", "0");
    }

}
