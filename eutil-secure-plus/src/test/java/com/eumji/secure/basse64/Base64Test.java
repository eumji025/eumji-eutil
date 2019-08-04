package com.eumji.secure.basse64;

import java.util.Base64;
import java.util.concurrent.CountDownLatch;

/**
 * base64 工具类简单总结
 * 对于base64 存在版本的更新
 *
 * 具体分析就是：
 * https://blog.csdn.net/java_4_ever/article/details/80978089
 *
 * 版本不一致使用的方式不同，对于老版和新版的协议不同推荐使用apache-common-decode
 *
 */
public class Base64Test {

	private static final CountDownLatch l = new CountDownLatch(500);

	private static final Base64.Decoder decoder = Base64.getMimeDecoder();

	private static final Base64.Encoder encoder = Base64.getMimeEncoder();

	/**
	 * BASE64解密
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String key) throws Exception {
		if (key == null || key.length() < 1) {
			return "";
		}
		return new String(decoder.decode(key));
	}

	/**
	 * BASE64加密
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String key) throws Exception {
		if (key == null || key.length() < 1) {
			return "";
		}
		return encoder.encodeToString(key.getBytes());
	}

	public static void main(String[] args) {
		//System.out.println(new BASE64Encoder().encode("admin:admin".getBytes()));
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					l.await();
					for (int i = 0; i < 1000; i++) {
						String a = new String(decoder.decode("YWRtaW46YWRtaW4="));
						if (!"admin:admin".equals(a)) {
							System.out.println(a);
							//break;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		for (int i = 0; i < 500; i++) {
			Thread t = new Thread(r);
			t.start();
			l.countDown();
		}
	}

}
