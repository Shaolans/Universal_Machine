package sum.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import sum.antlr.SUMgrammarLexer;
import sum.antlr.SUMgrammarParser;
import sum.interfaces.iast.IASTfactory;
import sum.interfaces.iast.IASTprogram;

public class SUMParser {
	protected IASTfactory factory;

	public SUMParser(IASTfactory factory) {
		this.factory = factory;
	}
	
	 public IASTprogram getProgram(String file) {

		ANTLRInputStream in = null;
		try {
			in = new ANTLRInputStream(readFile(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// flux de caractères -> analyseur lexical
		SUMgrammarLexer lexer = new SUMgrammarLexer(in);
		// analyseur lexical -> flux de tokens
		CommonTokenStream tokens =	new CommonTokenStream(lexer);
		// flux tokens -> analyseur syntaxique
		SUMgrammarParser parser =	new SUMgrammarParser(tokens);
		// démarage de l'analyse syntaxique
		SUMgrammarParser.ProgContext tree = parser.prog();		
		// parcours de l'arbre syntaxique et appels du Listener
		ParseTreeWalker walker = new ParseTreeWalker();
		SUMListener extractor = new SUMListener(factory);
		walker.walk(extractor, tree);	
		return tree.node;

    }
	 
	 
	public InputStream readFile(String file) {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner sc = new Scanner(new File(file));
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine()+"\n");
			}
			sc.close();
			//System.out.println(sb.toString());
			return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
