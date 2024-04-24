import java.util.Scanner;

public class T2 {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        int size = input.nextInt();
        int array[]  = new int[size];

        for(int i = 0; i < size; i++){
            array[i] = input.nextInt();
        }

        int sum = sum(array);
        System.out.println( "sum = "+ sum);
        System.out.println("average = "+ average(sum, size));
    }

    public static int sum(int[] array){
        int sum = 0;
        for(int i : array) sum += i;
        return sum;
    }

    public static float average (int sum , int size){
        if(size == 0){
            return 0;
        }
        return (sum / (float)size);
    }
}