import org.apache.commons.csv.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class BabyNames implements BabyInterface{
    CSVParser csvParser;
    int year;
    HashMap<Integer,String> Ranked;
    File[] filesInDir = new File("Java_Programming_Solving_Problems_with_Software/Baby Names MiniProject/us_babynames_by_year").listFiles();

    public BabyNames(int year){ this.year = year; }
    private CSVParser readFile(){
        BabyNames babyNames = this;
        int year = this.year;
        try{
            babyNames.csvParser= CSVParser.parse(new File("Java_Programming_Solving_Problems_with_Software/Baby Names MiniProject/us_babynames_by_year/yob"+year+".csv"), Charset.defaultCharset(),CSVFormat.RFC4180);
        }
        catch (Exception exception){ System.out.println("IOEXCEPTION"); }
        return babyNames.csvParser;
    }

    public void printNames(){
        CSVParser csvParser = this.readFile();
        for(CSVRecord record: csvParser){
            System.out.println(" Name "+record.get(0)+
                    " Gender "+record.get(1)+
                    " Num Born "+record.get(2));

        }
    }

    public int countNames(String gender){
        int counter =0;
        CSVParser csvParser = this.readFile();
        for(CSVRecord record: csvParser){
            if(record.get(1).equals(gender))
                counter++;

        }
        return counter;
    }

    public void totalBirths() {
        CSVParser csvParser = this.readFile();
        int totalBirths=0, totalBoys=0,totalGirls=0;
        for(CSVRecord csvRecord : csvParser){
            int numBorn = Integer.parseInt(csvRecord.get(2));
            totalBirths+=numBorn;
            if(csvRecord.get(1).equals("M")){
                totalBoys+=numBorn;
            }
            else totalGirls+=numBorn;
        }
        System.out.println("Total births "+totalBirths+" Girls "+totalGirls+
                " Boys "+totalBoys);
    }

    public int getRank(String name, String gender) {
        CSVParser csvParser = this.readFile();
        int rank=1;
        Ranked = new HashMap<Integer, String>();
        for(CSVRecord record:csvParser){
            if(record.get(1).equals(gender)){
                Ranked.put(rank,record.get(0));
                rank++;
            }
        }
        if(Ranked.containsValue(name))
            return getKey(Ranked, name);
        return -1;
    }

    private HashMap<Integer,String> getRanks(String gender) {
        int rank = 1;
        CSVParser csvParser = this.csvParser;  //TODO: Здесь хуйня с тем, что берётся на парсинг. ФИСКАНУТЬ
        Ranked = new HashMap<Integer, String>();
        for (CSVRecord record : csvParser) {
            if(record.get(1).equals(gender)){
                Ranked.put(rank,record.get(0));
                rank++;
            }
        }
        return Ranked;
    }

    private  <K, V> K getKey(Map<K, V> map, V value) throws NullPointerException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getName(int rank, String gender) {
        CSVParser csvParser = this.readFile();
        Ranked= getRanks(gender);
        for(CSVRecord record: csvParser)
            if(record.get(1).equals(gender)){
                return Ranked.get(rank);
            }
        return "NO NAME";

    }

    public void whatIsNameInYear(String name,int newYear, String gender){
        Ranked = this.getRanks(gender);
        System.out.println(String.format("%s born in %d would be %s if %s was born in %d",
                name,this.year,new BabyNames(newYear).getRanks(gender).get(getKey(Ranked,name)),gender,newYear));


    }
    public double yearOfHighestRank(String name, String gender) throws IOException {
        double result = 0;
        LinkedHashMap<Integer,String> Res= new LinkedHashMap<Integer,String>();
        for(File file: filesInDir){
            csvParser = CSVParser.parse(file, Charset.defaultCharset(),
                    CSVFormat.RFC4180);
            Ranked = getRanks(gender);
            for(String value: Ranked.values())
                if(value.equals(name))
                    Res.put(getKey(Ranked,name),file.getName());
        }
        System.out.println(Res.get(Collections.min(Res.keySet()))+ " Rank: "+Collections.min(Res.keySet()));
        for(Integer key: Res.keySet())
            result+=key;
        result/= Res.size();
        return result;
    }
    public double getAverageRank(String name, String gender) throws IOException {
        double result = yearOfHighestRank(name,gender);
        System.out.println(result);
        return result;
    }

    public int getTotalBirthsRankedHigher(String name, String gender) throws IOException {
        int numBorn = 0;
        CSVParser csvParser = this.readFile();
        for(CSVRecord record : csvParser){
            if(record.get(1).equals(gender))
                if(record.get(0).equals(name))
                    break;
                else
                    numBorn+=Integer.parseInt(record.get(2));
        }
        return numBorn;
    }



    public static void main(String[] args) throws IOException {

        BabyNames babyNames = new BabyNames(1905);
        //babyNames.printNames();
        System.out.println(babyNames.countNames("M"));
        //babyNames.totalBirths();
        //System.out.println(babyNames.getRank( "Frank","M"));
        //System.out.println(babyNames.getName( 430,"M"));
        //babyNames.whatIsNameInYear("Owen",2014,"M");
        //babyNames.yearOfHighestRank("Genevieve","F");
        //babyNames.getAverageRank("Susan","F");
        //System.out.println(babyNames.getTotalBirthsRankedHigher("Drew","M"));
    }
}
