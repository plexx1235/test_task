import com.fasterxml.jackson.databind.JsonNode;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import paths.IFilePaths;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    private static JsonExtractor extractor = new JsonExtractor();
    private static JsonGenerator generator = new JsonGenerator();
    private static CsvUtils csvUtils = new CsvUtils();
    private static final IFilePaths filePaths = ConfigFactory.create(IFilePaths.class);

    @BeforeAll
    static void setupTestData() {
        JsonNode companyJsonObject = generator.readJson(filePaths.companyJsonPath());
        JsonNode newCompanyObject = generator.changeJSONValues(companyJsonObject);
        generator.writeNewJson(newCompanyObject);
        JsonNode node = extractor.extractObjectByName(newCompanyObject, "customer");
        csvUtils.writeToCsv(node, filePaths.customerCsvPath());
    }

    @Test
    public void checkThatExtractedJsonObjectHasSameValuesAsInGeneratedCSV() {
        JsonNode jsonFromFile = generator.readJson(filePaths.newCompanyJsonPath());
        JsonNode node = extractor.extractObjectByName(jsonFromFile, "customer");
        csvUtils.readCsvToJson(filePaths.customerCsvPath());
        JsonNode customerJson = generator.readJson(filePaths.customerJsonPath());
        assertEquals(node, customerJson);
    }
}
