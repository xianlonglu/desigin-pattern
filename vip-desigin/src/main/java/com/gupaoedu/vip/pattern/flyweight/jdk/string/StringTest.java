package com.gupaoedu.vip.pattern.flyweight.jdk.string;

/**
 * Created by Tom.
 */
/**
 * Java 中将 String 类定义为 final（不可改变的），JVM 中字符串一般保存在字符串常量池中，</br>
 * java会确保一个字符串在常量池中只有一个拷贝，这个字符串常量池在 JDK6.0 以前是位于常量池中，位于 永久代， 而在 JDK7.0中，JVM
 * 将其从永久代拿出来放置于堆中。
 * 
 * @author Administrator
 *
 */
public class StringTest {
	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hello";
		String s3 = "he" + "llo";
		String s4 = "hel" + new String("lo");
		String s5 = new String("hello");
		String s6 = s5.intern();
		String s7 = "h";
		String s8 = "ello";
		String s9 = s7 + s8;
		System.out.println(s1 == s2);// true
		System.out.println(s1 == s3);// true
		System.out.println(s1 == s4);// false
		System.out.println(s1 == s9);// false
		System.out.println(s4 == s5);// false
		System.out.println(s1 == s6);// true
		/*
		 * String 类的 final 修饰的，以字面量的形式创建 String 变量时，JVM 会在编译期间就把该字面量
		 * "hello"放到字符串常量池中，由 Java 程序启动的时候就已经加载到内存中了。这个字符串常量池的特
		 * 点就是有且只有一份相同的字面量，如果有其它相同的字面量，JVM 则返回这个字面量的引用，如果没
		 * 有相同的字面量，则在字符串常量池创建这个字面量并返回它的引用。由于 s2 指向的字面量"hello"在常量池中已经存在了（s1 先于
		 * s2），于是 JVM 就返回这个字面量 绑定的引用，所以 s1==s2。 s3 中字面量的拼接其实就是"hello"，JVM
		 * 在编译期间就已经对它进行优化，所以 s1 和 s3 也是相 等的。s4 中的 new String("lo")生成了两个对象，lo，new
		 * String("lo")，lo 存在字符串常量池，new String("lo")存在堆中，String s4 = "hel" + new
		 * String("lo")实质上是两个对象的相加，编译器不会进 行优化，相加的结果存在堆中，而 s1
		 * 存在字符串常量池中，当然不相等。s1==s9 的原理一样。 s4==s5 两个相加的结果都在堆中，不用说，肯定不相等。 s1==s6
		 * 中，s5.intern()方法能使一个位于堆中的字符串在运行期间动态地加入到字符串常量池中
		 * （字符串常量池的内容是程序启动的时候就已经加载好了），如果字符串常量池中有该对象对应的字面
		 * 量，则返回该字面量在字符串常量池中的引用，否则，创建复制一份该字面量到字符串常量池并返回它 的引用。因此 s1==s6 输出 true。
		 */
	}
}
