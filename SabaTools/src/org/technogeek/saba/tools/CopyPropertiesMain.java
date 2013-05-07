/**
 * 
 */
package org.technogeek.saba.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author UDesai
 *
 */
public class CopyPropertiesMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File baseDir = null, targetDir = null;
		String[] locales = null;

		try {

			baseDir = new File(args[0]);
			locales = baseDir.list();

			targetDir = new File(args[1]);

			System.out.println("Dir : "+baseDir.getAbsolutePath());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Provide the path to the base directory of locales folder and the target directory where you want to copy the property files.");
			System.out.println("E.g., C:\\Users\\udesai\\Desktop\\locales");
			System.out.println("E.g., C:\\Users\\udesai\\Desktop\\properties");
			System.exit(0);
		}

		System.out.println("\n\n");

		for(String locale : locales) {

			File localeDir = new File(baseDir.getAbsolutePath()+"\\"+locale);
			File propDir = new File(localeDir.getAbsolutePath()+"\\reports\\properties");
			File prop = new File(propDir.getAbsolutePath()+"\\report_labels_"+locale+".properties");

			Path src = FileSystems.getDefault().getPath(propDir.getAbsolutePath(),prop.getName());
			Path target = FileSystems.getDefault().getPath(targetDir.getAbsolutePath(),prop.getName());

			try {
				Files.copy(src,target,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
