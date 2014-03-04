package gui;

import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = -113546151223878327L; // automatic generated
	private String name;
	private String content = null;
	private clojure.lang.PersistentList children = null;
	
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
	public Node(String name, String description, clojure.lang.PersistentList children) {
		this.name = name;
		this.content = description;
		this.children = children;
	}
	
	public clojure.lang.PersistentList getChildren() {
		return children;
	}
	
	public String getContent() {
		return this.content;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
