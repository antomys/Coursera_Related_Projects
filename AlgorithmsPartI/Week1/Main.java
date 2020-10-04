public class Main {
    public static void main(String[] args) {
        /*QuickUnion quickUnion = new QuickUnion(15);
        quickUnion.print();
        quickUnion.union(1,3);
        quickUnion.print();
        QuickFind quickFind = new QuickFind(15);
        quickFind.print();
        quickFind.union(1,3);
        quickFind.print();*/
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        //weightedQuickUnion.print();
        weightedQuickUnion.union(4,3);
        weightedQuickUnion.union(3,8);
        weightedQuickUnion.print();
    }
}
