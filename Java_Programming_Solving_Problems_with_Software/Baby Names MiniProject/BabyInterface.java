import org.apache.commons.csv.CSVParser;

import java.io.IOException;

interface BabyInterface {

    void printNames() throws IOException;

    void totalBirths();

    int getRank(String name, String gender);

    String getName(int rank, String gender);

    void whatIsNameInYear(String name,int newYear, String gender) throws IOException;

    double yearOfHighestRank(String name, String gender) throws IOException;

    double getAverageRank(String name, String gender) throws IOException;

    int getTotalBirthsRankedHigher(String name, String gender) throws IOException;

}
