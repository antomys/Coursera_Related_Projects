import java.lang.reflect.Array;

public class UncertaintiesImpl implements Uncertainties {
    private int[][] array;
    public UncertaintiesImpl(int[][] array){
        this.array = array;
    }

    public int WaldMaxModel() {
        System.out.println("Wald's (Minimax)\n Formula : E_mm = { min(j) a_ij}\t x* = Arg(max E_mm)\n");
        System.out.print("Max numbers: "); printVector(getMax());
        int [] MaxElem = getMax(getMax());
        System.out.print(String.format("The maximum of matrix is in {%d} row and is {{%d}\n ANSWER: Better to take x{%d} row",
                MaxElem[0], MaxElem[1], MaxElem[0]));
        return 0;

    }

    private int[] getMax (){
        int[] MaxVector = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > MaxVector[i]) {
                    MaxVector[i] = array[i][j];
                }
            }
        }
        return MaxVector;
    }

    private int[] getMax(int[]Vector) {
        int[] MaxElem = new int[2];
        MaxElem[1] = Vector[0];
        for (int i = 0; i < Vector.length; i++) {
            if (Vector[i] > MaxElem[1]) {
                MaxElem[1] = Vector[i];
                MaxElem[0] = i+1;
            }
        }
        return MaxElem;
    }

    private int[] getMin() {
        int[] MinVector = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            MinVector[i] = array[i][0];
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < MinVector[i]) {
                    MinVector[i] = array[i][j];
                }
            }
        }
        return MinVector;
    }
    private void printVector(int[]Vector) {
        for (int i = 0; i < Vector.length; i++) {
                System.out.print(Vector[i]+" ");
        }
    }

    public static void main(String[] args) {
/*                        {300,300,300,300},
                        {255,350,350,350},
                        {210,305,400,400},
                        {165,260,355,450}};*/


        int[][] Matrix = {
                {1,2,3,4},
                {5,1,2,3},
                {2,4,2,3}};
        UncertaintiesImpl un = new UncertaintiesImpl(Matrix);
        un.WaldMaxModel();
    }
}
