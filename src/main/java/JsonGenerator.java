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


    public JsonNode readJson(String pathName) throws IOException {
        File file = new File(pathName);
        JsonNode node = objectMapper.readValue(file, JsonNode.class);
        LOGGER.info("Json file was read\n" + node);
        return node;
    }

    public JsonNode changeJSONValues(JsonNode json) throws IOException {
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
    }

    public void writeNewJson(JsonNode json) throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(filePaths.newCompanyJsonPath()), json);
        LOGGER.info("new_company.json was saved\n" + json);
    }
}
