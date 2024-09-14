import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private static class SymbolInfo {
        boolean declared;
        boolean initialized;
        boolean used;

        SymbolInfo(boolean declared) {
            this.declared = declared;
            this.initialized = false;
            this.used = false;
        }
    }

    private final Map<String, SymbolInfo> symbols = new HashMap<>();

    public void declare(String name) {
        if (isDeclared(name)) {
            System.err.println("Variavel '" + name + "' ja foi declarada.");
        } else {
            symbols.put(name, new SymbolInfo(true));
        }
    }

    public void initialize(String name) {
        SymbolInfo info = symbols.get(name);
        if (info != null) {
            info.initialized = true;
        }
    }

    public void use(String name) {
        SymbolInfo info = symbols.get(name);
        if (info != null) {
            info.used = true;
        }
    }

    public boolean isDeclared(String name) {
        return symbols.containsKey(name);
    }

    public boolean isInitialized(String name) {
        SymbolInfo info = symbols.get(name);
        return info != null && info.initialized;
    }

    public void checkForErrors() {
        for (Map.Entry<String, SymbolInfo> entry : symbols.entrySet()) {
            String name = entry.getKey();
            SymbolInfo info = entry.getValue();
            if (!info.initialized) {
                System.err.println("Variavel '" + name + "' declarada mas nao inicializada.");
            }
            if (!info.used) {
                System.err.println("Variavel '" + name + "' declarada mas nao usada.");
            }
        }
    }
}
