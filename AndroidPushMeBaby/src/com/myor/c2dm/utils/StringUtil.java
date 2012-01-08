package com.myor.c2dm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 
 * Utils that helps String related work.
 * 
 * @author harry
 * 
 */
public class StringUtil {

	public static String convertStreamToString(InputStream is)
			throws IOException {

		/*
		 * To convert the InputStream to String we use the Reader.read(char[]
		 * buffer) method. We iterate until the Reader return -1 which means
		 * there's no more data to read. We use the StringWriter class to
		 * produce the string.
		 */
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String escapeHTMLTags(String htmlSourceString) {
		return htmlSourceString.replaceAll("\\<.*?\\>", "");
	}

	public static int convertStringToInt(String num) {

		// check if all chars in String is a digit
		for (int i = 0; i < num.length(); i++) {
			if (Character.isDigit(num.charAt(i))) {
				// it is fine. do nothing
			} else {
				// string is not a number, we cannot parse. just return -1.
				return -1;
			}
		}

		return Integer.parseInt(num);
	}

	private StringUtil() {

	}
}
