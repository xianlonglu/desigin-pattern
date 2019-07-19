package com.lxl.io.nio.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOutputDemo {
	static private final byte message[] = { 83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46 };
	static private final String filePath = "d://test.txt#bak";

	static public void main(String args[]) throws Exception {
		outFile();
		inFile();
	}

	private static void outFile() {
		/* 创建文件，向文件中写入数据 */
		try {
			/* 如果文件不存在，创建该文件,文件后缀是不是文本文件不重要 */
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			/* 根据文件输出流创建与这个文件相关的通道 */
			FileOutputStream fout = new FileOutputStream(file);
			FileChannel fc = fout.getChannel();
			/* 创建ByteBuffer对象， position = 0, limit = 64 */
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			for (int i = 0; i < message.length; ++i) {
				buffer.put(message[i]);
			}
			/* flip方法 position = 0, limit = 11 */
			buffer.flip();
			/* write方法使得ByteBuffer的position到 limit中的元素写入通道中 */
			fc.write(buffer);
			/* clear方法使得position = 0， limit = 64 */
			buffer.clear();

			/* 下面的代码同理 */
			buffer.put("你好，世界 456".getBytes("UTF-8"));
			buffer.flip();

			fc.write(buffer);
			buffer.clear();

			fout.close(); // 放到finally中
			fc.close(); // 放到finally中
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {

		}
	}

	private static void inFile() {
		/* 从刚才的文件中读取字符序列 */
		try {

			/* 通过Path对象创建文件通道 */
			Path path = Paths.get(filePath);
			FileChannel fc = FileChannel.open(path);

			ByteBuffer bb = ByteBuffer.allocate((int) fc.size() + 1);

			Charset utf8 = Charset.forName("UTF-8");

			/* 阻塞模式，读取完成才能返回 */
			fc.read(bb);

			bb.flip();
			CharBuffer cb = utf8.decode(bb);
			System.err.print(cb.toString());
			bb.clear();

			fc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}