
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Guilherme
 */
public class SymbolTable {

    private HashMap<String, Integer> table;
    private int nextAvailableAddress;

    public SymbolTable() {
        this.table = getTable();
        this.nextAvailableAddress = 16;
    }

    // Returns a hash map with the initial addresses
    private HashMap<String, Integer> getTable() {
        HashMap<String, Integer> table = new HashMap<>();
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
        table.put("R0", 0);
        table.put("R1", 1);
        table.put("R2", 2);
        table.put("R3", 3);
        table.put("R4", 4);
        table.put("R5", 5);
        table.put("R6", 6);
        table.put("R7", 7);
        table.put("R8", 8);
        table.put("R9", 9);
        table.put("R10", 10);
        table.put("R11", 11);
        table.put("R12", 12);
        table.put("R13", 13);
        table.put("R14", 14);
        table.put("R15", 15);
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);

        return table;
    }
    
    public int getNextAvailableAddress() {
        return nextAvailableAddress;
    }

    public void addEntry(String symbol) {
        table.put(symbol, nextAvailableAddress);
        nextAvailableAddress++;
    }
    
    public void addEntry(String symbol, int address) {
        table.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return table.containsKey(symbol);
    }

    public Integer getAddress(String symbol) {
        return table.get(symbol);
    }

}
