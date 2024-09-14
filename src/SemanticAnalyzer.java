import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.ParseTree;

public class SemanticAnalyzer extends CompBaseListener implements ParseTreeListener {
    private final SymbolTable symbolTable = new SymbolTable();

    @Override
    public void enterDefinevar(CompParser.DefinevarContext ctx) {
        for (var id : ctx.ID()) {
            symbolTable.declare(id.getText());
        }
    }

    @Override
    public void enterAssign(CompParser.AssignContext ctx) {
        String varName = ctx.ID().getText();
        if (!symbolTable.isDeclared(varName)) {
            System.err.println("Variavel '" + varName + "' declarada e nao usada.");
        } else {
            symbolTable.initialize(varName);
        }
    }

    @Override
    public void enterTermo(CompParser.TermoContext ctx) {
        if (ctx.ID() != null) {
            String varName = ctx.ID().getText();
            if (!symbolTable.isDeclared(varName)) {
                System.err.println("Variavel '" + varName + "' declarada e nao usada.");
            } else {
                symbolTable.use(varName);
            }
        }
    }

    public void analyze(ParseTree tree) {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
        symbolTable.checkForErrors();
    }
}
