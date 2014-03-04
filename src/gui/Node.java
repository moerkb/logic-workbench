package gui;

import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = -113546151223878327L; // automatic generated
	public String name;
	public String content = null;
	public clojure.lang.PersistentList children = null;
	public String path = null;
	
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
	public Node(String name, String description, String path, clojure.lang.PersistentList children) {
		this.name = name;
		this.content = description;
		this.children = children;
		this.path = path;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
