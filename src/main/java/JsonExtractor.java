import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonExtractor {
    private static final Logger LOGGER = LogManager.getLogger();

    public JsonNode extractObjectByName(JsonNode json, String nodeName) {
        JsonNode extractedNode = json.get(nodeName);
        LOGGER.info("JSON object was extracted \n" + extractedNode);
        return extractedNode;
    }

}
