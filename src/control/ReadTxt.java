package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ReadTxt {
	public double[] readTxt(String path) throws IOException {
		File file = new File(path);
		BufferedReader reader = null;
		String content = null;
		double[] result = new double[10000];
//		Vector rssult = new Vector();
		int i = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			while((content = reader.readLine()) != null ) {
				String[] lineTxt = content.split("：");
				result[i] = Double.valueOf(lineTxt[1]);
				i++;

			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
