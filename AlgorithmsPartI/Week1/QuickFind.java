public class QuickFind {
    private final int[] id;

    public QuickFind(int N){
        id= new int[N];
        for(int i = 0;i < N;i ++)
            id[i] = i;
    }
    boolean connected (int p , int q ){
        return id[p]==id[q];
    }
    public void union(int p , int q ){
        int i = id[p];
        int j = id[q];
        for(int ii=0;i<id.length;i++)
            if(id[ii]==i) id[ii]=j;
    }
    public void print(){
        for (int j : id) System.out.println(j + " ");
    }


}
