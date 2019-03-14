package io.vinson.common.encode;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2019/3/7
 */

public class Base64Util {

	public static final String encode(String data){
		byte[] bytes = data.getBytes();
		return Base64.encodeBase64String(bytes);
	}
	
	public static final String decode(String encodeData){
		byte[] bytes = Base64.decodeBase64(encodeData);
		return new String(bytes);
	}

	/**
	 * 将文件转成base64 字符串
	 * @return  *
	 * @throws Exception
	 */

	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);;
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return Base64.encodeBase64String(buffer);

	}

	/**
	 * 将base64字符解码保存文件
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */

	public static void decoderBase64File(String base64Code, String targetPath)
			throws Exception {
		byte[] buffer = Base64.decodeBase64(base64Code);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();

	}

	/**
	 * 将base64字符保存文本文件
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */

	public static void toFile(String base64Code, String targetPath)
			throws Exception {

		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}

	public static void main(String[] args) {
		String str = "djafogWJEOGJWE";
		
		String encodeData = encode(str);
		String decodeData = decode(encodeData);
		
		System.out.println(encodeData);
		System.out.println(decodeData);

		try {
			String encodedFile = encodeBase64File("D:\\mqtt-client-2.7z");
			decoderBase64File(encodedFile, "d:\\new-from-base64.7z");

			String encodedFile2 = encodeBase64File("e:\\QQ图片20160902181703.png");
			decoderBase64File(encodedFile2, "e:\\QQ图片20160902181703-from-base64.png");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
