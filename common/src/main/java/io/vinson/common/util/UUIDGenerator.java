package io.vinson.common.util;

import java.util.UUID;

/**
 * @Description: UUID生成工具类
 * @author: jiangweixin
 * @date: 2019/3/7
 */
public class UUIDGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(UUIDGenerator.getShortUUID(4));
	}
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
        "W", "X", "Y", "Z" };  


	public static String getShortUUID(int length) {
		StringBuffer shortBuffer = new StringBuffer();  
		String uuid = UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid);
		int offset = 32 / length;
		if(offset > 6) {
			offset = 6;
		}
		for (int i = 0; i < length; i++) {
		    String str = uuid.substring(i * offset, i * offset + offset);
		    int x = Integer.parseInt(str, 16);  
		    shortBuffer.append(chars[x % 0x3E]);  
		}  
		return shortBuffer.toString();  

	}  
	public static String getOrderIdByUUId() {
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 4 代表长度为4     
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        //String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return str;
    } 
}
