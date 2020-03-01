import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    private static JsonExtractor extractor = new JsonExtractor();
    private static JsonGenerator generator = new JsonGenerator();
    private static CsvUtils csvUtils = new CsvUtils();

    @BeforeAll
    static void setupTestData() throws Exception {
        JsonNode companyJsonObject = generator.readJson("src/main/resources/company.json");
        JsonNode newCompanyObject = generator.changeJSONValues(companyJsonObject);
        generator.writeNewJson(newCompanyObject);
        JsonNode node = extractor.extractObjectByName(newCompanyObject, "customer");
        csvUtils.writeToCsv(node, "src/main/resources/customer.csv");
    }

    @Test
    public void checkThatExtractedJsonObjectHasSameValuesAsInGeneratedCSV() throws IOException {
        JsonNode jsonFromFile = generator.readJson("src/main/resources/new_company.json");
        JsonNode node = extractor.extractObjectByName(jsonFromFile, "customer");
        csvUtils.readCsvToJson("src/main/resources/customer.csv");
        JsonNode customerJson = generator.readJson("src/main/resources/customer.json");
        assertEquals(node, customerJson);
    }
}
