package test;

public class Test {
	public static void main(String[] args) {
		System.out.println("classpath·���� "+Test.class.getClassLoader().getResource("").getPath());
		//��ȡ��ǰ��ļ���·��
        System.out.println("��ǰ�����·���� "+Test.class.getResource("").getPath());
	}
}
