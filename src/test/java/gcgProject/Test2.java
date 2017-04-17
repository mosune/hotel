package gcgProject;

public class Test2 {

	public static void main(String[] args) {
		String a = "/gcgProject//living/live/toChooseRoom.do";
		String b = a.substring(0, a.lastIndexOf("/"));
		String c = b.substring(0, b.lastIndexOf("/"));
		System.out.println(c.substring(c.indexOf("//") + 2));
	}

}
