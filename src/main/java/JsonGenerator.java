import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import paths.IFilePaths;
import schema_classes.Company;
import schema_classes.Customer;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextFloat;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class JsonGenerator {
    public static final IFilePaths filePaths = ConfigFactory.create(IFilePaths.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LogManager.getLogger();


    public JsonNode readJson(String pathName) {
        File file = new File(pathName);
        JsonNode node = null;
        try {
            node = objectMapper.readValue(file, JsonNode.class);
            LOGGER.info("Json file was read\n" + node);
        } catch (IOException e) {
            LOGGER.error("Input file not found! Terminating process");
            System.exit(1);
        }
        return node;
    }

    public JsonNode changeJSONValues(JsonNode json) {
        try {
            Company parsedJson = objectMapper.treeToValue(json, Company.class);
            parsedJson.setCompany(randomAlphabetic(5, 10));
            parsedJson.setCompanySize(nextInt());
            parsedJson.setCustomer(
                    new Customer(
                            randomAlphabetic(5, 10),
                            randomAlphabetic(5, 10),
                            randomAlphabetic(5, 10) + "@mail.com",
                            nextInt(),
                            nextFloat()));
            LOGGER.info("JSON values were changed");
            return objectMapper.valueToTree(parsedJson);
        } catch (JsonProcessingException e) {
            LOGGER.error("Something went wrong during JSON processing");
            return json;
        }
    }

    public void writeNewJson(JsonNode json) {
        try {
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(filePaths.newCompanyJsonPath()), json);
            LOGGER.info("new_company.json was saved\n" + json);
        } catch (IOException e) {
            LOGGER.error("Something went wrong during JSON writing process");
            e.printStackTrace();
        }
    }
}
