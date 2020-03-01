import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import schema_classes.Company;
import schema_classes.Customer;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextFloat;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class JsonGenerator {
     ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode readJson() throws IOException {
        File file = new File("src/main/resources/company.json");
        return objectMapper.readValue(file, JsonNode.class);
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
        return objectMapper.valueToTree(parsedJson);
    }

    public void writeNewJson(JsonNode json) throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File("src/main/resources/new_company.json"), json);
    }
}
