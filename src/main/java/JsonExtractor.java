import com.fasterxml.jackson.databind.JsonNode;

public class JsonExtractor {

    public JsonNode extractObjectByName(JsonNode json, String nodeName) {
        return json.get(nodeName);
    }

}
