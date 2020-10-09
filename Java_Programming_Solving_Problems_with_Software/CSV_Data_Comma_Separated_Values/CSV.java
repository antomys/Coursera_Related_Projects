import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

public interface CSV {

    void tester() throws IOException;

    String countryInfo(String country);

    void listExportersTwoProducts(String exportItem1, String exportItem2);

    int numberOfExporters(String exportItem);

    void bigExporters(String amount);

    CSVRecord coldestHourInFile();

    void testColdestHourInFile();

    String fileWithColdestTemperature() throws IOException;

    CSVRecord LowestHumidityInFile();

    void testLowestHumidityInFile();

    CSVRecord LowestHumidityInManyFiles() throws IOException;

    double averageTemperatureInFile();

    double averageTemperatureWithHighHumidityInFile(int value);
}
