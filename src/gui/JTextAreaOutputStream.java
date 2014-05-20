package gui;

import java.io.OutputStream;

import javax.swing.JTextArea;

public class JTextAreaOutputStream extends OutputStream {
	JTextArea ta;

	public JTextAreaOutputStream(JTextArea t) {
		super();
		ta = t;
	}

	public void write(int i) {
		ta.append(Character.toString((char) i));
	}

	public void write(char[] buf, int off, int len) {
		String s = new String(buf, off, len);
		ta.append(s);
	}

}
