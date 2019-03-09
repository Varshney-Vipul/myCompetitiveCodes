public class KingsCastle{
    static boolean gotResult = false;
    static long largeArea = 0;
    public static void calcLargestArea( String plotsOfEachCell[][],int A,int B,int C,int D, int budget,int plotPrices[],int numplots){
        //System.out.println("A: " + A +" B: " + B +" C: " + C +" D: " + D );
        long currentArea = (B - A + 1) * (D - C + 1);
        //System.out.println("Area of this block: "+currentArea);
        if(gotResult){
            if(currentArea <= largeArea){
                return;
            }
        }
        int priceOfArea = 0;
        boolean whichPlots[] = new boolean[numplots];
        for(int u = A; u<=B; u++){
            for(int v = C; v <=D ; v++){
                //System.out.println("u: "+u+" ,v: "+v +" , plots for this cell: " + plotsOfEachCell[u][v]);
                
                String plotsArray[] = plotsOfEachCell[u][v].split("-");
                for(int thisPlot= 0;thisPlot<plotsArray.length;thisPlot++){
                    if(!plotsArray[thisPlot].isEmpty()){
                        if(!whichPlots[Integer.valueOf(plotsArray[thisPlot])]){
                            whichPlots[Integer.valueOf(plotsArray[thisPlot])] = true;
                        }
                    }
                }
            }
        }
        priceOfArea = calcPrice(whichPlots, plotPrices);
        if(priceOfArea <= budget){
            //System.out.println("Found ---- cureentArea: "+currentArea+" , price: " + priceOfArea);
            gotResult = true;
            largeArea = currentArea;
            return;
        }
        //System.out.println("price of this area: "+priceOfArea);
        if(A+1<=B)
        calcLargestArea(plotsOfEachCell,A+1,B,C,D,budget,plotPrices,numplots);
        if(B-1>=A)
        calcLargestArea(plotsOfEachCell,A,B-1,C,D,budget,plotPrices,numplots);
        if(C+1<=D)
        calcLargestArea(plotsOfEachCell,A,B,C+1,D,budget,plotPrices,numplots);
        if(D-1>=C)
        calcLargestArea(plotsOfEachCell,A,B,C,D-1,budget,plotPrices,numplots);
    }
    static int calcPrice(boolean whichPlots[], int plotsPrice[]){
        int totalPrice = 0;
        
        for(int s=0;s<whichPlots.length;s++){
            if(whichPlots[s]){
                //System.out.println("plot included: "+s);
                totalPrice += plotsPrice[s];
            }
        }
        return totalPrice;
    }
    public static void main(String args[]){
    int testCases=0;
    java.util.Scanner in = new java.util.Scanner(System.in);
    testCases = in.nextInt();
    long results[] = new long[testCases];
    for(int k=0;k<testCases;k++){
        int m=0;
        int n=0;
        m = in.nextInt();
        n = in.nextInt();
        int c = 0;
        c = in.nextInt();
        
        //System.out.println("Budget for this test case: " + c);
        String plotsOfEachCell[][] = new String[m][n];

        // System.out.println("printing plots string matrix");
        // for(int sd=0;sd<m;sd++){
        //     for(int hg=0;hg<n;hg++){
        //         System.out.print(plotsOfEachCell[sd][hg]+" ");
        //     }
        //     System.out.println("");
        // }
        for(int sd=0;sd<m;sd++){
            for(int hg=0;hg<n;hg++){
                plotsOfEachCell[sd][hg]="";
            }
            // System.out.println("");
        }
        int numPlots = 0;
        numPlots = in.nextInt();
        int plotPrices[] = new int[numPlots];
        for(int u=0;u<numPlots;u++){
            int x,y,l,w,p;
            x = in.nextInt();
            y = in.nextInt();
            l = in.nextInt();
            w = in.nextInt();
            p = in.nextInt();
            plotPrices[u]= p;
            for(int li = x ; li < x + l; li++){
                for(int wi = y; wi < y + w; wi++){
                    plotsOfEachCell[li][wi] += "-"+ u;
                    //System.out.println("updated plot string for cell: (" + li +"  ,"+ wi +") is " + plotsOfEachCell[li][wi]);
                }
            }
            
        }
        calcLargestArea(plotsOfEachCell,0,m-1,0,n-1,c,plotPrices,numPlots);
        //System.out.println("Saving result for test case: " + k);
        results[k] = largeArea;
        largeArea = 0;
        gotResult = false;
    }
    for(int b=0;b<testCases;b++){
        System.out.println("Case " + b + ": " +results[b]);
    }
    
    }
}//1 4 4 6 3 1 0 2 1 2 2 0 1 4 2 0 3 3 1 4