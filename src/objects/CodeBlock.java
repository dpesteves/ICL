package objects;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

enum Operation{
	NUM,
	PLUS,
	MINUS,
	TIMES,
	DIV,
	ID,
	LET
}

public class CodeBlock {
	
	List<String> code;
	
	public CodeBlock() {
		code = new ArrayList<String>(100);
	}
	
	public void emit(String instruction) {
		code.add(instruction);
	}
	
	private void dumpHeader(PrintStream out, String filename) {
		out.println(".class public " + filename);
		out.println(".super java/lang/Object");
		out.println("");
		out.println(";");
		out.println("; standard initializer");
		out.println(".method public <init>()V");
		out.println("   aload_0");
		out.println("   invokenonvirtual java/lang/Object/<init>()V");
		out.println("   return");
		out.println(".end method");
		out.println("");
		out.println(".method public static main([Ljava/lang/String;)V");
		out.println("       ; set limits used by this method");
		out.println("       .limit locals 10");
		out.println("       .limit stack 256");
		out.println("");
		out.println("       ; setup local variables:");
		out.println("");
		out.println("       ;    1 - the PrintStream object held in java.lang.out");
		out.println("       getstatic java/lang/System/out Ljava/io/PrintStream;");
		out.println("");
		out.println("       ; place your bytecodes here");
		out.println("       ; START");
		out.println("");
	}

	private void dumpFooter(PrintStream out) {
		out.println("       ; END");
		out.println("");
		out.println("");		
		out.println("       ; convert to String;");
		out.println("       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		out.println("       ; call println ");
		out.println("       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		out.println("");		
		out.println("       return");
		out.println("");		
		out.println(".end method");
	}
	
	private void dumpCode(PrintStream out) {
		for( String s : code )
			out.println("       "+s);
	}
	
//	private String generateLabel() {
//		//TODO
//		return "";
//	}
//	
//	public String generateFrame() {
//		//TODO - nem sei se e string
//		return null;
//	}
	
	public void dump(String filename) throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream(filename));
		
		dumpHeader(out, filename);
		dumpCode(out);
		dumpFooter(out);
	}
	
}
