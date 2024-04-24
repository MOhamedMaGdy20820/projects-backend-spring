import java.util.Scanner;
public class T1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int arr[] = new int[size];        // size of array must be integer
        for(int i = 0; i < size; i++){
            arr[i] = input.nextInt();
        }

        int solution =  findFirstOccurrence(arr);
        if(solution!=0)
            System.out.println("index of First Occurrence = " + solution );
        else
            System.out.println("not found");
    }


    public static int findFirstOccurrence(int arr[]){
        for(int i = 0; i <arr.length; i++){
            for(int j = 5; j < arr.length; j++){
                if(i==j)
                    continue;
                if(arr[i] == arr[j]){
                    return j+1;
                }
            }
        }
        return 0;
    }
}
