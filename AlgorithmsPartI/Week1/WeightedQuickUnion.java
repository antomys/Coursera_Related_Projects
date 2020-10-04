public class WeightedQuickUnion {
    private final int[] id;
    private final int[] sz;

    public WeightedQuickUnion(int N){
        id = new int[N];
        sz = new int[N];
        for(int i = 0;i < N;i ++)
            id[i] = i;
    }
    private int root(int i){
        //todo: two-pass implementation: add second loop to root() to set
        //the id[] of each examined node to the root.
        while(i != id[i]) {id[i]=id[id[i]]; i = id[i]; }
        return i;
    }
    public boolean connected(int p, int q){
        return root(p)==root(q);
    }

    public void print(){
        for (int j : id) System.out.println(j);
    }
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        //id[i] = j;
        if(i == j)
            return;
        if(sz[i] < sz[j])
            id[i] = j; sz[j] += sz[i];
            id[j] = i; sz[i] += sz[j];

    }

}
