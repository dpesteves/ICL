package operations;

import objects.CodeBlock;
import objects.EnvironmentC;
import objects.EnvironmentI;

public class ASTTimes implements ASTNode{
	ASTNode lhs, rhs;
	
	public ASTTimes (ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public int eval(EnvironmentI env) {
		// TODO Auto-generated method stub
		return lhs.eval(env) * rhs.eval(env);
	}

	@Override
	public void compile(EnvironmentC env, CodeBlock cb, int level) {
		// TODO Auto-generated method stub
		lhs.compile(env, cb, level);
		rhs.compile(env, cb, level);
		cb.emit("imul");
	}

}
