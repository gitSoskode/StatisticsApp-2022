/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statsapp_22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.TextArea;

/**
 *
 * @author SosKode
 */
public class dispersion_variation {
    
    central_tendencies ctOB= new central_tendencies();
    
    public static double DIS_Ans;
    
      public static void percentile(double arr[], double value, TextArea screen){
        //Percentile is the ratio of count of values below to total count of values
        int countBelow=0, totalCount=arr.length;
        screen.appendText("\nPERCENTILE OF A DATA VALUE");
        screen.appendText("\n\nIt is the ratio of the number of values before to the total number of values\n");
        //Received and sorting in ascending order
          asReceivedThenSorted(arr, screen);
        Arrays.sort(arr);
        //count below the value
        for(int i = 0; i<arr.length;i++){
            if(arr[i]<value){
                countBelow++;
            }
            
        }
        screen.appendText("\n\npercentile  = (count below value) /(count total) \n=>"+countBelow+"/"+totalCount+"\n="+((double)countBelow/((double)totalCount))*100);
        DIS_Ans=((double)countBelow/((double)totalCount))*100;
    }
    
      //input and sorting
    public static void asReceivedThenSorted(double[] arr, TextArea screen){
        screen.appendText("\nThe data is:\n"+arr[0]);

        for(int i=1; i< arr.length; i++){
            screen.appendText(" , "+arr[i]);
        }
        screen.appendText("\nSorting data\n");

        Arrays.sort(arr);
        screen.appendText("\nThe sorted data now is:\n"); 
        screen.appendText(""+arr[0]);
        for(int i=1; i< arr.length; i++){
            screen.appendText(" , "+arr[i]);
        }
        
    }   //                                           RECEIVE AND SORT
    
    public static void rangeFinder(double arr[], TextArea screen){ //                                                    RANGE
        double lowestVal, highestVal, R;
        screen.appendText("FINDING THE RANGE OF AN ARRAY OF DATA\n\n");
        asReceivedThenSorted(arr, screen);
        screen.appendText("From the above data , the lowest and highest data values are \t");
        screen.appendText("\t"+arr[0]+"  and  "+arr[arr.length-1]+"  respectively"); 
        lowestVal=arr[0];
        highestVal=arr[arr.length-1];
        R=highestVal-lowestVal;
        screen.appendText("\nRange is the difference between the higest and the lowest values");
        screen.appendText("\n\nThus Range is "+R+"\n");
        DIS_Ans=R;
    }  
    
    public  double lowerQuartile(double []acc, TextArea screen){
        screen.appendText("Finding the lower quartile of a data set\n\n");
//        Arrays.sort(acc); 
        //finding the median of the first half to give the lower quartile
        screen.appendText("First find the median of the data set\n");
        ctOB.median(acc, acc.length, screen);
        double med;
        int firstHalfCount=acc.length/2;
        //median of first half to make LQ
        double lqArr[] = new double[firstHalfCount];
        screen.appendText("\nFind the median of the first half of data\n\n");
        screen.appendText("The first half of the data is: \n");
        System.arraycopy(acc, 0, lqArr, 0, firstHalfCount);
        double mLQ;
        
        for(int i=0; i<firstHalfCount;i++){
            screen.appendText("  \n"+lqArr[i]);
        }
        screen.appendText("\n\nFind the median\n");
        if(lqArr.length%2==0){
            mLQ=(lqArr[lqArr.length/2-1]+lqArr[lqArr.length/2])/2;
            screen.appendText("The number of values is even i.e "+lqArr.length);
            screen.appendText("\n\nThus the middle two values :\t"+lqArr[lqArr.length/2-1]+" and "+lqArr[lqArr.length/2]);
            screen.appendText("\n\nThe average of "+lqArr[lqArr.length/2-1]+" and "+lqArr[lqArr.length/2]+" is\n");
            screen.appendText(""+(lqArr[lqArr.length/2-1]+lqArr[lqArr.length/2])/2+"\n");
        }else{
            mLQ=lqArr[lqArr.length/2];
            screen.appendText("\nSince the number of data values is odd, \n the median is the middle single value: "+mLQ+"\n");
        }
        DIS_Ans=mLQ;
        return mLQ;
    }   //                                                      LOWER QUARTILE
    
    
    public  double upperQuartile(double[] acc, TextArea screen){
        asReceivedThenSorted(acc, screen);
        //find the 3/4 that is the 75% item
        int pos=acc.length;
        pos= (int) (pos*0.75);
        double uQ=acc[pos];
        forIntQ_upper=uQ;  //Initializing to be used by interquartile function
        screen.appendText("\nUpper-quartile is the 75th percentile \n thus, "+uQ+"\n");
        DIS_Ans=uQ;
        return uQ;
        
    }   //                                                      UPPER QUARTILE
    
     double forIntQLower, forIntQ_upper;
    public  double interQuartileRange(double[] acc, TextArea screen){
        forIntQLower=lowerQuartile(acc, screen); //finding the lower quartile before
        //sort array acc
        Arrays.sort(acc);
        //find the 3/4 that is the 75% item
        int pos=acc.length;
        pos= (int) (pos*0.75);
        double uQ=acc[pos];
        forIntQ_upper=uQ;  //Initiali...
        //The diff btn lower quartile and upper quartile is the interquartile range
        DIS_Ans=forIntQ_upper-forIntQLower;
        return forIntQ_upper-forIntQLower;
        
    }//                                                    INTER-QUARTILE RANGE
    
    static int nextVal=0;
    public static double varianceMeth(double arr[],int Size, TextArea screen){   //                                                                       VARIANCE
        //Using data input method above
        double Sum=0;
        StringBuilder sb = new StringBuilder();
        
        //find the mean
        screen.appendText("Find the mean\n\n");
        screen.appendText("mean = \t(Sum of values)/(number of values)");
        sb.append(arr[0]);//that is to avoid adding '+' char at the end of sum
        for(int i=1; i<Size; i++){
            //appending ['+ val']
            sb.append(" + "+arr[i]);
        }
        for(int j= 0; j<Size; j++){
            //for summing all array content from 0-<size
            Sum=Sum+arr[j];
        }
        screen.appendText("\n\nSum of values : "+sb.toString()+" =  "+Sum);
        screen.appendText("\n\nNumber of values: "+Size);
        screen.appendText("\nMean =  "+Sum+"/"+Size);
        double m =(Sum/Size);
        screen.appendText("\n\t= "+m);
        //finding the diff btn num and mean
        screen.appendText("\nValue\t\tDeviation\n");
        for(int i=0; i<Size; i++){
            screen.appendText(arr[i]+" \t\t"+arr[i]+" - "+m+"\t= "+(arr[i]-m)+"\n");
        }
        screen.appendText("");
        screen.appendText("");
        screen.appendText("\n\n|X-m|   \t(|X-m|)(|X-m|)\n");
        for(int i=0; i<Size; i++){
            screen.appendText(" "+Math.abs(arr[i]-m)+"\t\t"+(arr[i]-m)*(arr[i]-m)+"\n");
        }
        screen.appendText("\n\nSummation of square of absolute difference");
        double sumDevSq=0; 
        StringBuilder sbDevSq= new StringBuilder();//
        for(int i=0; i<Size; i++){
            sumDevSq+=(arr[i]-m)*(arr[i]-m);
        }
        sbDevSq.append((arr[0]-m)*(arr[0]-m));  //Essence of avoiding '+' at ending
        for(int i=1; i<Size; i++){
            sbDevSq.append(" + "+(arr[i]-m)*(arr[i]-m));
        }
        screen.appendText("\n"+sbDevSq.toString()+"\n");
        screen.appendText("\n=  "+sumDevSq);
        
        //dividing sum of squares by size of sample 'n'
        screen.appendText("\n\nDividing by sample size\n"+sumDevSq+"/"+Size);
        screen.appendText("\n\n = "+sumDevSq/Size);
        
        DIS_Ans=sumDevSq/Size;
        return sumDevSq/Size;
        
    }   //                                        VARIANCE
    
    public void coeffientOfVariationMth( ArrayList<Double> list, TextArea screen){
           screen.appendText("\nCOEFFICIENT OF VARIATION\n");
           screen.appendText("\nmean of numbers = sum/number\n Sum of all data");
           screen.appendText(""+list.get(0));
           for(int i=1; i<list.size(); i++){
               screen.appendText(" + "+list.get(i));
           }
           double sm =0;
           for(int i=0; i<list.size(); i++){
               sm+=list.get(i);
           }
           
           screen.appendText("\nThe mean ="+sm+"/"+list.size()+"\nHence, "+sm/list.size());
           //finding the deviatons
           screen.appendText("\nDeviation = value - mean\n");
           for(int i=0; i<list.size(); i++){
               screen.appendText("\n"+list.get(i)+" - "+sm/list.size()+" = "+(list.get(i)-sm/list.size()));
           }
           
           //squaring the deviatons
           screen.appendText("\nSquare of deviations\n");
           double sumOfSquare=0;
           screen.appendText(""+(list.get(0)-sm/list.size())*(list.get(0)-sm/list.size()));
           for(int a=1; a<list.size(); a++){
               screen.appendText(" + "+(list.get(a)-sm/list.size())*(list.get(a)-sm/list.size()));
           }
           for(int a=0; a<list.size(); a++){
               sumOfSquare+=(list.get(a)-sm/list.size())*(list.get(a)-sm/list.size());
           }
           screen.appendText("\nVariance = "+sumOfSquare+"/"+list.size()+"\n= "+sumOfSquare/list.size());
           screen.appendText("\nSTANDARD DEVIATION is the square root of the variance  = "+sumOfSquare/list.size()+"^0.5\n"+"thus, "+Math.sqrt(sumOfSquare/list.size()));
           screen.appendText("\nCoefficient of variance\n = Standard deviation/mean\n=> ("+Math.sqrt(sumOfSquare/list.size())+"/"+sm/list.size()+")X100%\n= "+100*Math.sqrt(sumOfSquare/list.size())/(sm/list.size()));
       }
    
    
    //The end
}
