/** IN PROGRESS
*problem link:https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/altf4-and-hackerearths-problem-setting/
 */
public class altf4_and_hackerearth_problem_setting {
    public static void main(String args[]){
        java.util.Scanner in = new java.util.Scanner(System.in);
        int testCases = in.nextInt();
        for(int h=0;h<testCases;h++){
            int N = in.nextInt();
            int K = in.nextInt();
            int MinAmount[] = new int[N];
            int tasks[] = new int[N];
            int prices[] = new int[N];
            int totalTasks = 0;
            for(int j=0;j<N;j++){
                prices[j] = in.nextInt();
            }
            for(int j=0;j<N;j++){
                tasks[j] = in.nextInt();
            }
            for(int j=0;j<N;j++){
                totalTasks += tasks[j];
                if(j==0){
                    MinAmount[j] = (K + tasks[j]) * prices[j];
                }else{
                    int alonePrice = prices[j] * (totalTasks + K);
                    int otherPrice = MinAmount[j-1] + ((K+tasks[j]) * prices[j]);
                    if(alonePrice <  otherPrice ){
                        MinAmount[j] = alonePrice;
                    } else {
                        MinAmount[j] = otherPrice;
                    }
                }
            }
            System.out.println(MinAmount[N-1]);
        }   
    }
}