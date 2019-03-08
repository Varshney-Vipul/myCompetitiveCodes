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
            int optimumAmount[] = new int[N+1];
            int tasks[] = new int[N+1];
            int prices[] = new int[N+1];
            int howFar[] = new int[N+1];
            //int positionTask[][] = new int[N][N];
            int totalTasks = 0;
            for(int j=1;j<=N;j++){
                prices[j] = in.nextInt();
            }
            for(int j=1;j<=N;j++){
                tasks[j] = in.nextInt();
            }
            for(int j=1;j<N;j++){
                int sameProMoney = (tasks[j] + K) * prices[j];
                howFar[j] = j;
                for(int i=j+1;i<=N;i++){
                    if(sameProMoney > (tasks[j] * prices[i])){
                        //System.out.println("set");
                        howFar[j] = i;
                    }else{
                        break;
                    }
                }
            }
            for(int u=1;u<=N;u++){  
                System.out.println("tasks: "+tasks[u]+" --- K: "+K+" -- prices: " + prices[u]);
                int lastManJob = (tasks[u] + K) * prices[u];
                // if(u==0){
                //     optimumAmount[u] = lastManJob;
                // }
                System.out.println("N value:  " + u + " -------lastmanjob::" + lastManJob);
                for(int y=u-1;y>=0;y--){
                    System.out.println("how far value for y: " + y + " and how far value: "+ howFar[y]);
                    if(howFar[y] >= u){
                        System.out.println("here");
                        int tempMoney = lastManJob + (prices[u] * tasks[y]);
                        System.out.println("tempmoney : "+tempMoney+"  -- optimunamount y-1 : " + optimumAmount[y-1] + " -- optimunamount y :" + optimumAmount[y] +" -- lastmanjob: " + lastManJob );
                        if( (optimumAmount[y] + lastManJob) > (optimumAmount[y-1] + tempMoney)){
                            optimumAmount[u] =  optimumAmount[y-1] + tempMoney;
                            lastManJob +=  (prices[u] * tasks[y]);  
                        }else{
                            optimumAmount[u] = optimumAmount[y] + lastManJob;
                            break;
                        }
                    }else{
                        System.out.println("there");
                         System.out.println(   " -- optimunamount y :" + optimumAmount[y] +" -- lastmanjob: " + lastManJob );
                       
                        optimumAmount[u] = optimumAmount[y] + lastManJob;
                        break;
                    } 
                }
            }
            System.out.println(optimumAmount[N]);
        }   
    }
}