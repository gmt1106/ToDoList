package observer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Weather {

    public Weather() throws IOException {

        BufferedReader br = null;

        String API_KEY = "1f35274ea9bc85b713891e0e79cf61f3";
        String LOCATION = "Vancouver,CA";
        String theURL = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";

        try {

            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String finalString = sb.toString();

            System.out.println(finalString);


            JSONObject jsonObject = new JSONObject(finalString);
//            String[] names = JSONObject.getNames(jsonObject);
//            for (String string : names) {
//                System.out.println(string);
//            }


            JSONObject jsonObject1 = jsonObject.getJSONObject("main");
            System.out.println( jsonObject1.getDouble("temp"));


            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonObject2 = jsonArray.getJSONObject(0);
            System.out.println( jsonObject2.getString("main"));


        } catch (Exception e){

            if (br != null) {
                br.close();
            }
        }

    }
}
