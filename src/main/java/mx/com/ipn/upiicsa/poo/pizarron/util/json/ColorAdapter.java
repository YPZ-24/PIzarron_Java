package mx.com.ipn.upiicsa.poo.pizarron.util.json;

import java.awt.Color;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ColorAdapter implements JsonSerializer<Color>, JsonDeserializer<Color>{

	@Override
	public Color deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		String colorTxt = json.getAsString();
		Color color = new Color(Integer.valueOf(colorTxt.substring(1,3), 16), Integer.valueOf(colorTxt.substring(3,5), 16), Integer.valueOf(colorTxt.substring(5,7), 16));
		return color;
	}

	@Override
	public JsonElement serialize(Color color, Type typeOfSrc, JsonSerializationContext context) {
		int R = color.getRed();
		int G = color.getGreen();
		int B = color.getBlue();
		String rgb = "#" + getColorInTwoValues(R) + getColorInTwoValues(G) + getColorInTwoValues(B); 
		return new JsonPrimitive(rgb);
	}
	
	private String getColorInTwoValues(Integer color) {
		return String.format("%2s", Integer.toHexString(color)).replace(" ", "0");
	}
	
}
