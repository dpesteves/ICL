package objects;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentC {
	
	static class Assoc {
		public int level;
		public String frame;
		
		Assoc(int level) {
			this.level = level;
			this.frame = "x" + level;
		}
	}
	
	EnvironmentC ancestor;
	Map<String, Assoc> map;
	
	public EnvironmentC(int level) {
		ancestor = null;
		
		map = new HashMap<String, Assoc>();
		map.put("", new Assoc(level));
	}
	
	public EnvironmentC(EnvironmentC anc, int level) {
		ancestor = anc;
		
		map = new HashMap<String, Assoc>();
		map.put("", new Assoc(level));
	}
	
	public void assoc(String key, int value) {
		map.put(key, new Assoc(value));
	}
	
	public Assoc find(String key) {
		return map.getOrDefault(key, new Assoc(Integer.MIN_VALUE));
	}
	
	public EnvironmentC beginScope(int level){
		return new EnvironmentC(this, level);
	}
	
	public EnvironmentC endScope(){
		return ancestor;
	}
}
