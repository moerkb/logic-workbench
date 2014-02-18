package gui;

public class Node {
	
	private String name;
	private String proposition;
	private clojure.lang.PersistentList children = null;
	
	public Node(String name, String proposition) {
		this.name = name;
		this.proposition = proposition;
	}
	
	public Node(String name, clojure.lang.PersistentList children) {
		this.name = name;
		this.children = children;
	}
	
	public clojure.lang.PersistentList getChildren() {
		return children;
	}
	
	public String getProposition() {
		return proposition;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
