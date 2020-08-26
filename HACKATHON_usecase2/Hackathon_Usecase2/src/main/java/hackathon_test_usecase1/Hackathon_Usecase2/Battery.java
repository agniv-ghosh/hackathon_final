package hackathon_test_usecase1.Hackathon_Usecase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class Battery {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("Battery.txt");
			BufferedReader br = new BufferedReader(fr);

			String line;
			JSONObject finaljson = new JSONObject();

			String fore_grd = "";
			String battery_drain = "";
			float battery = 0.0f;

			while ((line = br.readLine()) != null) {
				if (line.contains("Foreground activities")) {
					String str[] = line.split("\\s+");

					fore_grd = str[3] + " " + str[4] + " " + str[5] + " " + str[6] + " " + str[7] + " " + str[8];

					finaljson.put("Foreground_time", fore_grd);
				}

				if (line.contains("Uid u0a202")) {
					String str1[] = line.split("\\s+");

					battery = Float.parseFloat(str1[3]);
					battery_drain = String.format("%.03f", battery);
					battery = Float.parseFloat(battery_drain);
					
					float percentage = battery / 1000;
					String per = String.format("%.03f", percentage);
					

					finaljson.put("Battery_percentage", per);
					finaljson.put("Battery_drain", battery_drain);
				}
			}

			System.out.println(finaljson);

			FileWriter file = new FileWriter("Output2_Battery.json");
			file.write(finaljson.toJSONString());
			file.close();
			fr.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
