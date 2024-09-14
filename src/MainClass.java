import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) throws IOException {
        System.out.println("A diante! ");

        // Cria o lexer para ler o arquivo de entrada
        CompLexer lexer = new CompLexer(CharStreams.fromFileName("input.txt"));

        // Cria o token stream a partir do lexer
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        // Cria o parser para processar os tokens
        CompParser parser = new CompParser(tokenStream);

        // Faz o parsing do arquivo de entrada
        ParseTree tree = parser.program();

        // Análise semântica
        SemanticAnalyzer analyzer = new SemanticAnalyzer();
        analyzer.analyze(tree);

        // Geração de código
        CodeGenerator generator = new CodeGenerator();
        generator.generateCode(tree);

        // Exibe a árvore de parsing em uma janela gráfica
        JFrame frame = new JFrame("Parse Tree");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.5); // Ajuste o zoom da árvore
        panel.add(viewer);
        frame.add(new JScrollPane(panel));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Tamanho da janela
        frame.setVisible(true);

        System.out.println("Programa executado com sucesso.");
    }
}