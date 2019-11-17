package operations;

import objects.CodeBlock;
import objects.EnvironmentC;
import objects.EnvironmentI;

public interface ASTNode {
	public int eval(EnvironmentI env);
	
	public void compile(EnvironmentC env, CodeBlock cb, int level);
}
