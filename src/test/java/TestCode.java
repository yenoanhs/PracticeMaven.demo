import java.io.IOException;

import init.MyProperties;

public class TestCode {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MyProperties prop = new MyProperties();
		System.out.println("GeckoDriver"+prop.getString("GeckoDriver"));
		System.out.println("path of GeckoDriver"+prop.getPath("GeckoDriver"));

	}

}
