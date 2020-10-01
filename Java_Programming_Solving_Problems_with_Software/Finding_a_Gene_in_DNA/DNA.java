import edu.duke.*;
public class DNA {

    public static String findSimpleGene(String gene) {
        //String res = "";
        int startIndex, currIndex;
        gene.toUpperCase();
        startIndex = gene.indexOf("ATG");
        if (startIndex == -1) gene = "";
        currIndex = gene.indexOf("TAA", startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 != 0) {
                currIndex = gene.indexOf("TAA", currIndex + 1);
                //System.out.println(gene);
            }
            break;
        }
        gene = gene.substring(startIndex, currIndex+3);
        return  gene;
    }
    private static int findStopCodon (String dnaSrt,
                              int startIndex,
                              String stopCodon){
        int currIndex = dnaSrt.indexOf(stopCodon,startIndex+3);
        while(currIndex!=-1){
            if((currIndex-startIndex)%3==0)
                return currIndex;
            else
                currIndex=dnaSrt.indexOf(stopCodon,currIndex+1);
        }
        return -1;
    }
    public static String findGene(String dna, int where){

        int startIndex = dna.indexOf("ATG",where);
        if(startIndex==-1) return "";
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        //int minIndex = Math.min(taaIndex,Math.min(tgaIndex,tagIndex));
        int minIndex=0;

        if(taaIndex==-1||(tgaIndex!=-1&&tgaIndex<taaIndex)) minIndex=tgaIndex;
        else minIndex=taaIndex;

        if(minIndex==-1 || (tagIndex!=-1&&tagIndex<minIndex)) minIndex=tagIndex;

        if(minIndex==-1) return "";

        if(minIndex==dna.length())
            return "";
        return dna.substring(startIndex,minIndex+3);
    }
    public static StorageResource findMultipleGenes(String dnaStr){
        StorageResource storageResource = new StorageResource();
        int startIndex = 0 ;
        while(true){
            String currentGene= findGene(dnaStr,startIndex);
            if(currentGene.isEmpty())
                break;
            storageResource.add(currentGene);
            //System.out.println(currentGene);
            startIndex=dnaStr.indexOf(currentGene,startIndex)+currentGene.length();
        }
        return storageResource;
    }
    private static void printStorage(StorageResource storageResource){
        for(String s: storageResource.data())
            System.out.println(s);
    }
    public static int countGenes(String dnaStr){
        int startIndex = 0, count=-1;
        while(true){
            String currentGene= findGene(dnaStr,startIndex);
            count++;
            if(currentGene.isEmpty())
                break;
            System.out.println(currentGene);
            startIndex=dnaStr.indexOf(currentGene,startIndex)+currentGene.length();
        }
        return count;
    }

    private static int howMany(String stringa, String stringb){
        int count = 0, index=0;
        while(true){
            index=stringa.indexOf(stringb,index);
            if(index!=-1){
                count++;
                index+=stringb.length();
            }
            else break;
        }
    return count;
    }

    public static void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 ||index > input.length() - 4) {
                break;
            }
            System.out.println("index " + index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            System.out.println("index after updating " + index);
        }
    }

    public static float cgRation(String dna){
        dna.toUpperCase();
        float Gs = howMany(dna,"C");
        float Cs = howMany(dna,"G");
        Gs+=Cs;
        Cs=Gs/dna.length();
        return Cs;
    }

    public static void processGenes (StorageResource storageResource) {
        int count = 0, count1 = 0;
        String genes;
        StorageResource storageResource1 = new StorageResource();
        for (String s : storageResource.data()) {
            storageResource1 = findMultipleGenes(s);
        }
        for (String s : storageResource1.data()) {
            if (s.length() >= 60) {
                count++;
            }
            if (cgRation(s) > 0.35) {
                count1++;
            }
        }
        System.out.println("count:"+count+"\n count1:"+count1);
    }

    public static void test() {
        findAbc("abcabcabcabca");
    }

    public static void testSimpleGene(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        StorageResource dna = new StorageResource();
        StorageResource storageResource = new StorageResource();
        String dna1 = fr.asString();
        dna1= dna1.toUpperCase();
        dna.add(dna1);
        System.out.println("count genes"+countGenes(dna1));
        processGenes(dna);
        String str = "ATGACAATCCTAAACATATGTGAACCTAACACTGGAGCTCCCAAATTTATAAAACAATTACTAGTAGACATAAGAAATAAGATAGACAGCAACACAATAATAGTGGGGGACTTCAATACTCCACTGACAGCACTAGACAGGTCATCAAGACAGAAAGTCAACAAAGAAACAATGGATTTAAACTATACTTTGGAACAAATGGACTTAACAGATATATATAGAACATTTCATCCAACAACCACAGAATACACATTCTATTCAACAGCACATGGAATTTTCTCCAAGATAGACCATATGATAGGCCATAAAATGAGTCTCAATAAATTTAAGAAAATTGAAATTGTATCACGCACTCTCTCACATCACAATGGAATAAAACTGAAAATCAACTCCAAAAGGAATCTTCGAAACCATGCAAATACATGGAAATTAAATAACCTGCTCCTGAATGAGCATTGGGTGAAAAACGAAATCAAGATGGAAATGTAA";
        System.out.println(str.length());
        System.out.println("CTG "+ howMany(dna1,"CTG"));
        //System.out.println(dna1);
        //System.out.println(countGenes(dna1));
        /*findMultipleGenes(dna);
        StorageResource genes = findMultipleGenes(dna);
        printStorage(genes);*/

        //System.out.println(howMany(dna,"ATG")+" "+ countGenes(dna));
    }
    public static void main(String[] args) {
        testSimpleGene();
       // test();

    }
}
