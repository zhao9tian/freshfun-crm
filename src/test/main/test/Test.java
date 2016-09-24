package test;

public class Test {
	public static void main(String[] args) {
		System.out.println("classpath路径： "+Test.class.getClassLoader().getResource("").getPath());
		//获取当前类的加载路径
        System.out.println("当前类加载路径： "+Test.class.getResource("").getPath());
	}
}
