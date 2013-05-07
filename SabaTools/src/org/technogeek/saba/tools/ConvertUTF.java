/**
 * 
 */
package org.technogeek.saba.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author UDesai
 *
 */
public class ConvertUTF {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File baseDir = null, targetDir = null;
		String[] locales = null;

		try {

			baseDir = new File(args[0]);
			locales = baseDir.list();

			targetDir = new File(args[1]);

			System.out.println("Dir : "+baseDir.getAbsolutePath());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Provide the path to the base directory & target diractory of properties folder.");
			System.out.println("E.g., C:\\Users\\udesai\\Desktop\\properties");
			System.exit(0);
		}

		System.out.println("\n\n");

		for(String locale : locales) {

			File src = new File(baseDir.getAbsolutePath()+"\\"+locale);
			File target = new File(targetDir.getAbsolutePath()+"\\"+locale);

			System.out.println("\tFile : "+src.getAbsolutePath());

			Charset charset = Charset.defaultCharset();
			CharsetDecoder decoder = charset.newDecoder();
			CharsetEncoder encoder = charset.newEncoder();
			BufferedReader br = null;
			BufferedWriter bw = null;
			String buffer = null;

			try {

				br = new BufferedReader(new FileReader(src));
				bw = new BufferedWriter(new FileWriter(target));

				do {

					buffer = br.readLine();
					if(buffer!=null) {
						ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(buffer));
						CharBuffer cbuf = decoder.decode(bbuf);
	
						bw.write(cbuf.toString()+"\n");
					}
				}while(buffer!=null);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(CharacterCodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				br.close();
				bw.close();
			}
		}
	}

}
