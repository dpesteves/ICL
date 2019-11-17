package operations;

import java.util.Map;

import objects.CodeBlock;
import objects.EnvironmentC;
import objects.EnvironmentI;

public class ASTLet implements ASTNode {

	Map<String, ASTNode> map;
	ASTNode body;
	
	public ASTLet (Map<String, ASTNode> map, ASTNode body) {
		this.map = map;
		this.body = body;
	}
	
	@Override
	public int eval(EnvironmentI env) {
		// TODO Auto-generated method stub
		EnvironmentI env2 = env.beginScope();
		
		for(Map.Entry<String, ASTNode> entry : map.entrySet()) {
			int a = entry.getValue().eval(env);
			env2.assoc(entry.getKey(), a);
		}
		
		env = env2;
		
		int b = body.eval(env);
		env = env.endScope();
		
		return b;
	}

	@Override
	public void compile(EnvironmentC env, CodeBlock cb, int level) {
		// TODO Auto-generated method stub
		int currentLevel = level + 1;
		
		for(Map.Entry<String, ASTNode> entry : map.entrySet()) {
			env.assoc(entry.getKey(), currentLevel);
		}
		
		String frameId = "f" + currentLevel;
		cb.emit("new " + frameId);
		cb.emit("dup");
		cb.emit("invokespecial " + frameId + "/<init>()V");
		cb.emit("dup");
		cb.emit("aload " + currentLevel);
	}

}
