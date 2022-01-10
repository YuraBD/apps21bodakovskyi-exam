package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final List<JsonPair> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new ArrayList<>();
        for (JsonPair jsonPair : jsonPairs) {
            this.add(jsonPair);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonBody() + "}";
    }

    private String getJsonBody() {
        StringBuilder jsonStr = new StringBuilder();
        Iterator<JsonPair> jsonIterator = jsonPairs.iterator();
        while (jsonIterator.hasNext()) {
            JsonPair json = jsonIterator.next();
            jsonStr
                    .append("'")
                    .append(json.key)
                    .append("'")
                    .append(": ")
                    .append(json.value.toJson());
            if (jsonIterator.hasNext()) {
                jsonStr.append(", ");
            }
        }
        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        for (int i = 0; i < jsonPairs.size(); i++) {
            if (jsonPairs.get(i).key.equals(jsonPair.key)) {
                jsonPairs.set(i, jsonPair);
                return;
            }
        }
        jsonPairs.add(jsonPair);
    }

    public Json find(String name) {
        for (JsonPair jsonPair : jsonPairs) {
            if (jsonPair.key.equals(name)) {
                return jsonPair.value;
            }
        }
        return null;
    }

    public boolean contains(String name) {
        for (JsonPair jsonPair : jsonPairs) {
            if (jsonPair.key.equals(name)) {
                return true;
            }
        }
        return false;
    }


    public JsonObject projection(String... names) {
        JsonObject newJsonObj = new JsonObject();
        for (JsonPair jsonPair : this.jsonPairs) {
            if (Arrays.asList(names).contains(jsonPair.key)) {
                newJsonObj.add(jsonPair);
            }
        }
        return newJsonObj;
    }
}