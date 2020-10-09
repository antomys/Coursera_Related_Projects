import edu.duke.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

class CSVImpl implements CSV{

    private static File file = null;
    CSVParser csvParser = null;
    CSVImpl() throws IOException { }

    public static void main(String[] args) throws IOException {
        CSVImpl csv = new CSVImpl();
       file = new File
                ("Java_Programming_Solving_Problems_with_Software/CSV_Data_Comma_Separated_Values/nc_weather/2013/");
        csv.MultipleTester();
    }

    public void tester() throws IOException {
        CSVImpl csv = this;
        //Scanner scanner = new Scanner(System.in);
        String path =
                "Java_Programming_Solving_Problems_with_Software/CSV_Data_Comma_Separated_Values/nc_weather/2013/weather-2013-09-02.csv";
        file= new File(path);
        csvParser= CSVParser.parse(file, Charset.defaultCharset(),CSVFormat.RFC4180.withHeader());
       // csv.testLowestHumidityInFile();
        /*csv.listExportersTwoProducts("cotton", "flowers");
        System.out.println(csv.numberOfExporters("cocoa"));*/
        //csv.bigExporters("$999,999,999,999");
        //csv.coldestHourInFile();
        //System.out.println(csv.averageTemperatureWithHighHumidityInFile(80));
        //System.out.println(csv.averageTemperatureWithHighHumidityInFile(80));
        //System.out.println(csv.averageTemperatureInFile());
        /*csv.listExportersTwoProducts("fish","nuts");
        System.out.println(csv.numberOfExporters("gold"));
        System.out.println(csv.countryInfo("Nauru"));
        csv.bigExporters("$999,999,999,999");*/
    }

    private  void MultipleTester() throws IOException{
        CSVImpl csv = this;
        CSVRecord coldest;
        file = new File
                ("Java_Programming_Solving_Problems_with_Software/CSV_Data_Comma_Separated_Values/nc_weather/2013/");
        //System.out.println(csv.fileWithColdestTemperature());
        coldest = csv.coldestHourInManyFiles();
        System.out.println("Lowest Humidity"+ " "+ coldest.get("DateUTC") + "  " + coldest.get("TemperatureF") + " " );
    }

    public String countryInfo(String country) {
        CSVParser parser = this.csvParser;
        for(CSVRecord record: parser){
            if(record.get("Country").contains(country))
                return "Country: "+record.get("Exports") + ": "+record.get("Value (dollars)");
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(String exportItem1, String exportItem2) {
        CSVParser parser = this.csvParser;
        for(CSVRecord record:parser){
            if(record.get("Exports").contains(exportItem1)&&record.get("Exports").contains(exportItem2))
                System.out.println(record.get("Country"));
        }
    }

    public int numberOfExporters(String exportItem) {
        int res=0;
        CSVParser parser = this.csvParser;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)) res+=1;
        }
        return res;
    }

    public void bigExporters(String amount) {
        CSVParser parser = this.csvParser;
        for(CSVRecord record: parser){
            if(record.get("Value (dollars)").length()>amount.length())
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
        }

    }


    public CSVRecord coldestHourInFile() {
        CSVParser parser = this.csvParser;
        CSVRecord coldest = null;
        for(CSVRecord record: parser){
            coldest = minOfTwo(coldest,record, "TemperatureF");
        }
        //System.out.println(coldest.get("TemperatureF"));
        return coldest;
    }
    public CSVRecord coldestHourInManyFiles() throws IOException {
        CSVImpl csv = this;
        CSVRecord coldest = null, currentRow;
        File[] filesInDir = file.listFiles();
        for(File file: filesInDir){
            csvParser = CSVParser.parse(file, Charset.defaultCharset(),CSVFormat.RFC4180.withHeader());
            currentRow = csv.coldestHourInFile();
            coldest = minOfTwo(coldest,currentRow,"TemperatureF");
        }
        return coldest;
    }

    private CSVRecord minOfTwo(CSVRecord largestSoFar, CSVRecord curRow, String column){
        if(largestSoFar==null)
            largestSoFar = curRow;
        else
            if(Double.parseDouble(curRow.get(column))<Double.parseDouble(largestSoFar.get(column))) largestSoFar=curRow;

        return largestSoFar;
    }

    public void testColdestHourInFile() {
        CSVImpl csv = this;
        CSVRecord csvRecord= csv.coldestHourInFile();
        System.out.println("Coldest was "+ csvRecord.get("TemperatureF")+ " at "+ csvRecord.get("TimeEST"));

    }

    public String fileWithColdestTemperature() throws IOException {
        CSVImpl csv = this;
        CSVRecord coldest = null, currentRow = null;
        File[] filesInDir = file.listFiles();
        for(File file: filesInDir){
            csvParser = CSVParser.parse(file, Charset.defaultCharset(),CSVFormat.RFC4180.withHeader());
            currentRow = csv.coldestHourInFile();
            coldest = minOfTwo(coldest,currentRow,"TemperatureF");
        }
        return (coldest.get("DateUTC") + " " + coldest.get("TemperatureF"));
    }

    public CSVRecord LowestHumidityInFile() {
        CSVParser parser = this.csvParser;
        CSVRecord coldest = null;
        for(CSVRecord record: parser){
            coldest = minOfTwo(coldest,record, "Humidity");
        }
        //System.out.println(coldest.get("DateUTC")+" at "+ coldest.get("Humidity"));
        return coldest;
    }

    public void testLowestHumidityInFile() {
        CSVImpl csv = this;
        CSVRecord csvRecord= csv.LowestHumidityInFile();
        System.out.println("Lowest Humidity was  "+ csvRecord.get("Humidity")+ " at "+ csvRecord.get("DateUTC"));

    }

    public CSVRecord LowestHumidityInManyFiles() throws IOException {
        CSVImpl csv = this;
        CSVRecord coldest = null, currentRow;
        File[] filesInDir = file.listFiles();
        for(File file: filesInDir){
            csvParser = CSVParser.parse(file, Charset.defaultCharset(),CSVFormat.RFC4180.withHeader());
            currentRow = csv.LowestHumidityInFile();
            coldest = minOfTwo(coldest,currentRow,"Humidity");
        }
        return coldest;
    }

    public double averageTemperatureInFile() {
        ArrayList<Double> temps = new ArrayList<Double>();
        CSVParser parser = this.csvParser;
        for(CSVRecord record: parser){
            temps.add(Double.parseDouble(record.get("TemperatureF")));
        }
        double sum=0;
        for(double i:temps)
            sum+=i;

        return (sum/temps.size());
    }

    public double averageTemperatureWithHighHumidityInFile(int value) {
        CSVParser parser = this.csvParser;
        ArrayList<Double> temps = new ArrayList<Double>();
        for(CSVRecord record: parser)
            if(Double.parseDouble(record.get("Humidity"))>=value)
                temps.add(Double.parseDouble(record.get("TemperatureF")));
        if(temps.isEmpty())
            return 0;
        double sum=0;
        for(double i:temps)
            sum+=i;
        return (sum/temps.size());
    }
}