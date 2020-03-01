import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import schema_classes.Customer;

import java.io.File;
import java.io.FileWriter;

public class CsvWriter {
    public void writeToCsv(JsonNode jsonNode) throws Exception {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .schemaFor(Customer.class)
                .withHeader()
                .withColumnSeparator(',');

        ObjectWriter objectWriter = csvMapper.writer(csvSchema);
        File csvFile = new File("src/main/resources/customer.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        objectWriter.writeValue(fileWriter, jsonNode);
        fileWriter.close();
    }
}
