import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator extends CompBaseListener {
    private final StringBuilder javaCode = new StringBuilder();
    private final StringBuilder cCode = new StringBuilder();

    @Override
    public void enterProgram(CompParser.ProgramContext ctx) {
        javaCode.append("public class Main {\npublic static void main(String[] args) {\n");
        cCode.append("#include <stdio.h>\nint main() {\n");
    }

    @Override
    public void exitProgram(CompParser.ProgramContext ctx) {
        javaCode.append("}\n}\n");
        cCode.append("return 0;\n}\n");
    }

    @Override
    public void enterDefinevar(CompParser.DefinevarContext ctx) {
        String type = ctx.type().getText();
        String javaType = convertTypeToJava(type);
        String cType = convertTypeToC(type);

        for (var id : ctx.ID()) {
            javaCode.append(javaType).append(" ").append(id.getText()).append(";\n");
            cCode.append(cType).append(" ").append(id.getText()).append(";\n");
        }
    }

    @Override
    public void enterAssign(CompParser.AssignContext ctx) {
        String varName = ctx.ID().getText();
        String expr = ctx.expr().getText();

        javaCode.append(varName).append(" = ").append(expr).append(";\n");
        cCode.append(varName).append(" = ").append(expr).append(";\n");
    }

    @Override
    public void enterIf_statement(CompParser.If_statementContext ctx) {
        javaCode.append("if (").append(ctx.condition().getText()).append(") {\n");
        cCode.append("if (").append(ctx.condition().getText()).append(") {\n");
    }

    @Override
    public void exitIf_statement(CompParser.If_statementContext ctx) {
        javaCode.append("}\n");
        cCode.append("}\n");
        if (ctx.bloco(1) != null) {
            javaCode.append("else {\n");
            cCode.append("else {\n");
            javaCode.append(ctx.bloco(1).getText()).append("\n");
            cCode.append(ctx.bloco(1).getText()).append("\n");
            javaCode.append("}\n");
            cCode.append("}\n");
        }
    }

    private String convertTypeToJava(String type) {
        return switch (type) {
            case "numeros" -> "int";
            case "booleano" -> "boolean";
            case "texto" -> "String";
            default -> "Object";
        };
    }

    private String convertTypeToC(String type) {
        return switch (type) {
            case "numeros" -> "int";
            case "booleano" -> "int"; 
            case "texto" -> "char*";
            default -> "void*";
        };
    }

    public void generateCode(ParseTree tree) throws IOException {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        try (FileWriter javaWriter = new FileWriter("output.java");
             FileWriter cWriter = new FileWriter("output.c")) {
            javaWriter.write(javaCode.toString());
            cWriter.write(cCode.toString());
        }
    }
}
