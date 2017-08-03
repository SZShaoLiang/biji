package cn.tedu.test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

public class TestCase {
	
	@Test
	public void testMd5() {
		String str = "12345678";
		String md5 = DigestUtils.md5Hex(str);
		System.out.println(md5);
	}
	
	@Test
	public void testSaltPwd() throws Exception {
		String pwd = "123";
		String salt = "你吃了吗?";
		String s = DigestUtils.md5Hex(pwd+salt);
		System.out.println(s);
		//23f97b1171789885989406b36f3d4035
		
		//
	}
}

























