package gui;


public class Node {

	public String name;
	public String content = null;
	public clojure.lang.PersistentVector children = null;
	public String path = null;
	public boolean isProject = false;
	
	/**
	 * Generates a node with formula.
	 */
	public Node(String name, String proposition) {
		this.name = name;
		this.content = proposition;
	}
	
	/**
	 * Generates a project with formulas
	 */
	public Node(String name, String description, String path, clojure.lang.PersistentVector children) {
		this.name = name;
		this.content = description;
		this.children = children;
		this.path = path;
		this.isProject = true;
	}
	
	/**
	 * Generates a m4 file
	 */
	public Node(String name, String content, String path) {
		this.name = name;
		this.content = content;
		this.path = path;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
