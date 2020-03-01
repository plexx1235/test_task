import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.aeonbits.owner.ConfigFactory;
import paths.IFilePaths;
import schema_classes.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvUtils {
    public static final IFilePaths filePaths = ConfigFactory.create(IFilePaths.class);
    public void writeToCsv(JsonNode jsonNode, String pathName) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .schemaFor(Customer.class)
                .withHeader()
                .withColumnSeparator(',');

        ObjectWriter objectWriter = csvMapper.writer(csvSchema);
        File csvFile = new File(pathName);
        FileWriter fileWriter = new FileWriter(csvFile);
        objectWriter.writeValue(fileWriter, jsonNode);
        fileWriter.close();
    }

    public void readCsvToJson(String targetCsvPath) throws IOException {
        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();
        ObjectMapper mapper = new ObjectMapper();
        Customer readAll = csvMapper.readerFor(Customer.class).with(csvSchema).readValue(new File(targetCsvPath));
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePaths.customerJsonPath()), readAll);
    }
}
