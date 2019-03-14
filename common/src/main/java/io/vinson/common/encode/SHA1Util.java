package io.vinson.common.encode;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA1Util {

	public static String encode(String data){
		return DigestUtils.shaHex(data);
	}
}
