import com.fasterxml.jackson.databind.JsonNode;

public class JsonExtractor {

    public JsonNode extractObjectByName(JsonNode company, String nodeName) {
        return company.get(nodeName);
    }

}
