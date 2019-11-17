package operations;

import java.io.FileNotFoundException;

import objects.CodeBlock;
import objects.EnvironmentC;
import objects.EnvironmentI;

public class ASTId implements ASTNode {
	String id;

	public ASTId(String id) {
		this.id = id;
	}

	@Override
	public int eval(EnvironmentI env) {
		// TODO Auto-generated method stub
		int eval = Integer.MIN_VALUE;
		
		do {
			if(env.find(id) != Integer.MIN_VALUE) {
				eval = env.find(id);
				break;
			}
			else
				env = env.endScope();
		} while(env != null);
		
		return eval;
	}

	@Override
	public void compile(EnvironmentC env, CodeBlock cb, int level) {
		// TODO Auto-generated method stub
		int currLevel = level + 1;
		cb.emit("aload SL");
		for(int i=0; i<currLevel; i++)
			cb.emit(String.format("getfield f%s/sl f%s", currLevel - i, level - i));
		cb.emit(String.format("getfield f%s/%s Ljava/lang/Object", currLevel, id));
		try {
			cb.dump("compile");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
