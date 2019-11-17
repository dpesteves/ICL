package operations;

import objects.CodeBlock;
import objects.EnvironmentC;
import objects.EnvironmentI;

public class ASTNum implements ASTNode {
	private int eval;
	//private EnvironmentI e;
		
	public ASTNum(int eval) {
		this.eval = eval;
	}

	@Override
	public int eval(EnvironmentI env) {
		// TODO Auto-generated method stub
		return eval;
	}

	@Override
	public void compile(EnvironmentC env, CodeBlock cb, int level) {
		// TODO Auto-generated method stub
		cb.emit("sipush" + eval);
	}
	
}
