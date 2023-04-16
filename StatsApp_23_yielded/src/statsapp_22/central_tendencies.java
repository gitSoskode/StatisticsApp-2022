/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statsapp_22;

import java.util.Arrays;
import javafx.scene.control.TextArea;

/**
 *
 * @author SosKode
 */
public class central_tendencies {
    //Array to acculate input values
    double[] OriginalArr = new double[100];
    int Count=0;  //
    public double CT_Ans;
    
    public void central_tendencies(double[] OriginalArr,int Count){
        this.OriginalArr=OriginalArr;
        this.Count=Count;
        System.out.println("the array is "+OriginalArr[3]);
        System.out.println("the count is "+OriginalArr[3]);
    }
    public void mean(double []arr, int C, TextArea screen){
        
        double Sum=0;
        StringBuilder sb = new StringBuilder();
        
        //find the mean
        screen.appendText("\nFinding the mean\n\n");
        screen.appendText("\nmean = \t(Sum of values)/(number of values)");
        Sum=Sum+arr[0];  //summing from index 0
        sb.append(arr[0]);//that is to avoid adding '+' char at the end of sum
        for(int i=1; i<C; i++){
            //appending ['+ val']
            sb.append(" + "+arr[i]);
            Sum=Sum+arr[i];
        }
        screen.appendText("\nSum of values : "+sb.toString()+" = "+Sum);
        screen.appendText("\n\nNumber of values: "+C);
        double m =(Sum/C);
        screen.appendText("\n\nThe mean = (∑X)/n\n = "+Sum+"/"+C+"\n");
        screen.appendText("\nmean\t= "+m);
        CT_Ans=m;
    }
    
    public void median(double[] arr, int C, TextArea Screen ){
        double med;
        Screen.appendText("Calculating median");
        Screen.appendText("\n\nThe data as received is \n");
        Screen.appendText("\n\n"+arr[0]);
        for(int i=1; i<C; i++){
            Screen.appendText(" , "+arr[i]);
        }
        Screen.appendText("\n\nSorting data in ascending order");
        Arrays.sort(arr);
        Screen.appendText("\n\n"+arr[0]);
        for(int i=1; i<C; i++){
            Screen.appendText(" , "+arr[i]);
        }
        //use the size to determine if oddity
        if(C%2==0){
            //that's even count
            Screen.appendText("\n\nThe data values are "+C+" in number.");
            Screen.appendText("\n\nThe middle two values are "+arr[C/2-1]+" and "+arr[C/2]);
            Screen.appendText("\n\nFinding the average of the two :\n("+arr[C/2-1]+" + "+arr[C/2]+")/2");
            Screen.appendText("\n\n = "+(arr[C/2-1]+arr[C/2])+"/2");
            Screen.appendText("\n\nTherefore the median is "+(arr[C/2-1]+arr[C/2])/2);
            CT_Ans=(arr[C/2-1]+arr[C/2])/2;
        }else{
            
            med=arr[(int)C/2];
            Screen.appendText("\n\nThe data values are "+arr.length+" in number.");
            Screen.appendText("\n\nSince the number of values is odd, the middle single value is the median.");
            Screen.appendText("\n\nHence, "+med);
            CT_Ans=med;
        }
    }
    public void mode(double[] arr, int C, TextArea Screen ){
        
    }
    public void weightedMean(double[] arr, double []weights, int C, TextArea Screen ){
        /*
        *The weighted mean is the sum of the product of data value and it's weight, divided by the sum of all weights
        */      //sop(sum of prod)
        double sumWeights=0, sop=0;
        
        //initialize weightArr
        //input data values
        Screen.appendText("\n\nEnter corresponding weights in same order as data");
        for(int i=0; i<weights.length; i++){
            sumWeights+=weights[i];
        }
        Screen.appendText("\n\nSum of weights= "+sumWeights);
        //multiplying data values and their corresponding weights
        Screen.appendText("\n\nProducts of data value and corresponding weight:");
        for(int i=0; i<weights.length; i++){
            sop+=arr[i]*weights[i];
            Screen.appendText(arr[i]+" X "+weights[i]);
        }
        Screen.appendText("\n\nRatio of Sum of products to sum of weights");
        Screen.appendText("\n\nWeighted mean = sum of products / sum of weights");
        Screen.appendText("\n\nwm = "+sop+"/"+sumWeights);
        Screen.appendText("\n\nwm = "+sop/sumWeights);
        CT_Ans=sop/sumWeights;
    }
    
    
    
}
