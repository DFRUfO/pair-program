package control;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteTxt {
	public void WriteTxt(String content,String path) throws IOException {
		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(path,true))); //不覆盖前面的内容
		out.write(content + "\r\n");
		out.close();
	}

}
