package paths;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:paths.properties")
public interface IFilePaths extends Config {

    @Config.Key("company.json_path")
    String companyJsonPath();

    @Config.Key("customer.json_path")
    String customerJsonPath();

    @Config.Key("new_company.json_path")
    String newCompanyJsonPath();

    @Config.Key("customer.csv_path")
    String customerCsvPath();


}
