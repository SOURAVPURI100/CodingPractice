import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BinaryTest {

	public static void main(String[] args) {
		File file = new File("/home/sourav/Documents/cellmon_json/58fe4f0368bed92f24e14cbc/day/1493251200000.bin");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str = "";
			while ((str = in.readLine()) != null) {
				System.out.println(str);

			}
		} catch (Exception e) {

		}
	}

	// Path path =
	// Paths.get("/home/sourav/Documents/cellmon_json/58fe4f0368bed92f24e14cbc/day/1493251200000.bin");
	// byte[] data = Files.readAllBytes(path);

}
