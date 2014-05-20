package gui;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileLocator {

	public static String getProgrammRoot() throws UnsupportedEncodingException {
		return (new File(URLDecoder.decode(
				FileLocator.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
				"UTF-8"))).getParent();
	}
}
