package objects;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentI {
	Map<String, Integer> map;
	EnvironmentI ancestor;
	
	public EnvironmentI() {
		map = new HashMap<String, Integer>();
		ancestor = null;
	}
	
	public EnvironmentI(EnvironmentI env) {
		map = new HashMap<String, Integer>();
		ancestor = env;
	}
	
	public EnvironmentI beginScope(){
		return new EnvironmentI(this);
	}
	
	public EnvironmentI endScope(){
		return ancestor;
	}
	
	public void assoc(String key, int value) {
		map.put(key, value);
	}
	
	public int find(String key) {
		return map.getOrDefault(key, Integer.MIN_VALUE);
	}
}
