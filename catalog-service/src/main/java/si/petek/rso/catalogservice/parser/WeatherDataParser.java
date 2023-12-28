package si.petek.rso.catalogservice.parser;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WeatherDataParser {
    private List<String> maxTemp;
    private List<String> minTemp;
    private List<String> weatherCode;
    private List<String> dates;

    private List<String> weatherConditions;

    public WeatherDataParser(String jsonString) {
        parseJson(jsonString);
    }

    private void parseJson(String jsonString) {
        JSONObject json = new JSONObject(jsonString);

        JSONObject dailyData = json.getJSONObject("daily");
        JSONArray datesArray = dailyData.getJSONArray("time");
        JSONArray weatherCodeArray = dailyData.getJSONArray("weather_code");
        JSONArray maxTempArray = dailyData.getJSONArray("temperature_2m_max");
        JSONArray minTempArray = dailyData.getJSONArray("temperature_2m_min");

        dates = new ArrayList<>();
        weatherCode = new ArrayList<>();
        maxTemp = new ArrayList<>();
        minTemp = new ArrayList<>();
        weatherConditions = new ArrayList<>();

        for (int i = 0; i < datesArray.length(); i++) {
            dates.add(datesArray.getString(i));
            weatherCode.add(String.valueOf(weatherCodeArray.getInt(i)));
            maxTemp.add(String.valueOf(maxTempArray.getDouble(i)));
            minTemp.add(String.valueOf(minTempArray.getDouble(i)));
        }
        transformCodeToDescription();
    }

    private void transformCodeToDescription(){
        for (String code : weatherCode){
            weatherConditions.add(getDescriptionFromCode(code));
        }
    }

    private String getDescriptionFromCode(String code){
        List<String> table = new ArrayList<String>();
        table.add("Cloud development not observed or not observable");
        table.add("Clouds generally dissolving or becoming less developed");
        table.add("State of sky on the whole unchanged");
        table.add("Clouds generally forming or developing");
        table.add("Visibility reduced by smoke, e.g. veldt or forest fires, industrial smoke or volcanic ashes");
        table.add("Haze");
        table.add("Widespread dust in suspension in the air, not raised by wind at or near the station at the time of observation");
        table.add("Dust or sand raised by wind at or near the station at the time of observation, but no well developed dust whirl(s) or sand whirl(s), and no duststorm or sandstorm seen");
        table.add("Well developed dust whirl(s) or sand whirl(s) seen at or near the station during the preceding hour or at the time ot observation, but no duststorm or sandstorm");
        table.add("Duststorm or sandstorm within sight at the time of observation, or at the station during the preceding hour");
        table.add("Mist");

        table.add("Patches");
        table.add("More or less continuous");
        table.add("Lightning visible, no thunder heard");
        table.add("Precipitation within sight, not reaching the ground or the surface of the sea");
        table.add("Precipitation within sight, reaching the ground or the surface of the sea, but distant, i.e. estimated to be more than 5 km from the station");
        table.add("Precipitation within sight, reaching the ground or the surface of the sea, near to, but not at the station");
        table.add("Thunderstorm, but no precipitation at the time of observation");
        table.add("Squalls at or within sight of the station during the preceding hour or at the time of observation");
        table.add("Funnel cloud(s) at or within sight of the station during the preceding hour or at the time of observation");

        table.add("Drizzle (not freezing) or snow grains ");
        table.add("Rain (not freezing)");
        table.add("Snow");
        table.add("Rain and snow or ice pellets");
        table.add("Freezing drizzle or freezing rain");
        table.add("Shower(s) of rain");
        table.add("Shower(s) of snow, or of rain and snow");
        table.add("Shower(s) of hail*, or of rain and hail");
        table.add("Fog or ice fog");
        table.add("Thunderstorm (with or without precipitation)");
        table.add("Slight or moderate duststorm or sandstorm");
        table.add("Severe duststorm or sandstorm");
        table.add("Slight or moderate blowing snow");
        table.add("Heavy drifting snow");
        table.add("Slight or moderate blowing snow");
        table.add("Heavy drifting snow");
        table.add("Fog or ice fog at a distance at the time of observation, but not at the station during the preceding hour, the fog or ice fog extending to a level above that of the observer");
        table.add("Fog or ice fog in patches");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");
        table.add("Snow");



        if (Integer.parseInt(code) > table.size()){
            return table.get(10);
        }
        return table.get(Integer.parseInt(code));
    }
}
