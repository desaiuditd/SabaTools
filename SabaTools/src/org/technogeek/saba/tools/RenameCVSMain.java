/**
 * 
 */
package org.technogeek.saba.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author UDesai
 *
 */
public class RenameCVSMain {
	
	
	private static void rename(File f) throws IOException {

		BufferedReader br;
		BufferedWriter bw;
		String data = null,temp = null;

		System.out.print("\tFile : "+f.getAbsolutePath());
		
		br = new BufferedReader(new FileReader(f));

		data="";
		do {
			temp = br.readLine();
			if (temp!=null) data += temp;
		}while(temp!=null);
		
		br.close();

		boolean flag = data.contains("yshah") && !data.contains("udesai");

		data = data.replaceAll("yshah", "udesai");

		bw = new BufferedWriter(new FileWriter(f));
		bw.write(data);
		bw.close();

		System.out.println(" ...... done : "+flag);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File patchesDir = null,cvs,root;
		String[] versionDir = null, patches = null;

		/*
		 * base directory for patches
		 */
		
		try {

			patchesDir = new File(args[0]);
			versionDir = patchesDir.list();

			System.out.println("Dir : "+patchesDir.getAbsolutePath());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Provide the path to the base directory of patches folder.");
			System.out.println("E.g., C:\\Users\\udesai\\Desktop\\patches");
			System.exit(0);
		}

		/*
		 * 
		 * Base CVS for patches folder
		 * Change the name for root dir
		 */

		cvs = new File(patchesDir.getAbsolutePath()+"\\CVS");
		root = new File(cvs.getAbsolutePath()+"\\Root");

		rename(root);

		System.out.println("\n\n");

		/*
		 * Folders for other versions and their individual patch folders
		 */

		for (String version : versionDir) {

			File dir = new File(patchesDir.getAbsolutePath()+"\\"+version);			// getting the individual version directory

			if(dir.isDirectory() && !version.equalsIgnoreCase("cvs")) {				// Only directories needs to be processed

				System.out.println("Dir : "+dir.getAbsolutePath());

				cvs = new File(dir.getAbsolutePath()+"\\CVS");						// Base CVS for version directory
				root = new File(cvs.getAbsolutePath()+"\\Root");

				rename(root);


				System.out.println("\n\n");

				patches = dir.list();												// Patches for individual version
				
				for (String patch : patches) {

					File patchDir = new File(dir.getAbsoluteFile()+"\\"+patch);

					if(patchDir.isDirectory() && !patch.equalsIgnoreCase("cvs") && !patch.equalsIgnoreCase("New Folder") && !patch.equalsIgnoreCase("test")) {

						cvs = new File(patchDir.getAbsolutePath()+"\\CVS");					// CVS for patch
						root = new File(cvs.getAbsolutePath()+"\\Root");
	
						rename(root);
					}
				}
			}
		}
	}

}
