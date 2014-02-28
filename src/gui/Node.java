package gui;

import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = -113546151223878327L; // automatic generated
	private String name;
	private String proposition = null;
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
