package gui;

import java.io.File;

public class FileLocator {

	
	public static String getProgrammRoot() {
		return (new File(FileLocator.class.getProtectionDomain().getCodeSource().getLocation().getPath())).getParent();
	}
	
}
