package hackathon_test_usecase1.Hackathon_Usecase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class Memory {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("Memory.txt");
			BufferedReader br = new BufferedReader(fr);

			String line;
			int i = 1;
			float sum = 0.0f;
			float max = 0.0f;

			JSONObject values = new JSONObject();
			JSONObject finaljson = new JSONObject();

			int c = 0, size = 0;
			while ((line = br.readLine()) != null) {
				++c;
				if (c % 2 == 0) {
					size++;
					String splitvalues[] = line.split("\\s+");

					float fval = Float.parseFloat(splitvalues[2]);
					String str = String.format("%.02f", fval);
					fval = Float.parseFloat(str);
					
					float fval_conv = fval / 1024.0f;
					str = String.format("%.02f",fval_conv);
					fval_conv = Float.parseFloat(str);
					if (fval_conv > max)
						max = fval_conv;
					sum += fval_conv;

					String seconds = i + "s";
					values.put(seconds, (fval_conv + ""));
					i++;
				}
			}

			float avg = (float) sum / size;
			finaljson.put("values", values);
			finaljson.put("MaxMemory(MB)", (String.format("%.2f", max)));
			finaljson.put("AverageMemory(MB)", (String.format("%.2f", avg)));
			finaljson.put("Usecasename", "Homepage");

			System.out.println(finaljson);

			FileWriter file = new FileWriter("Output1_Memory.json");
			file.write(finaljson.toJSONString());
			file.close();
			fr.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
