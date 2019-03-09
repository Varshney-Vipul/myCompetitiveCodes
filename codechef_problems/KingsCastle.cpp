#include<iostream>
using namespace std;
int gotResult = 0;
long largeArea = 0;
void calcLargestArea(const int m,const int n,const float priceArray[][n],int A,int B,int C,int D,const float budget){
    long currentArea = (B - A) * (D - C);
    if(gotResult){
        if(currentArea <= largeArea){
            return;
        }
    }
    float priceOfArea = 0;
    for(int u = A; u<=B; u++){
        for(int v = C; v <=D ; v++){
            priceOfArea += priceArray[u][v];
        }
    }
    if(priceOfArea <= budget){
        gotResult = 1;
        largeArea = currentArea;
        return;
    }
    if(A+1<=B)
    calcLargestArea(m,n,priceArray,A+1,B,C,D);
    if(B-1>=A)
    calcLargestArea(m,n,priceArray,A,B-1,C,D);
    if(C+1<=D)
    calcLargestArea(m,n,priceArray,A,B,C+1,D);
    if(D-1>=C)
    calcLargestArea(m,n,priceArray,A,B,C,D-1);
}
int main(){
    int testCases;
    long results[testCases] = {0};
    cin>>testCases;
    for(int k=0;k<testCases;k++){
        int m=0;
        int n=0;
        cin>>m>>n;
        float c = 0;
        cin>>c;
        float priceOfEachCell[m][n] = {0};
        int l = 0;
        cin>>l;
        for(int u=0;u<l;u++){
            int x,y,l,w,p;
            cin>>x>>y>>l>>w>>p;
            float pricePerCell = p / (w * l);
            for(int li = x ; li < x + l; li++){
                for(int wi = y; wi < y + w; wi++){
                    priceOfEachCell[li][wi] += pricePerCell;
                }
            }
        }
        calcLargestArea(m,n,priceOfEachCell,0,m-1,0,n-1);
        results[k] = largeArea;
        largeArea = 0;
        gotResult = 0;
    }
    for(int b=0;b<testCases;b++){
        cout<<"Case "<<b<<": "<<results[b];
    }
    return 0;
}