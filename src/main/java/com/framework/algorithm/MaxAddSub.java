package com.framework.algorithm;

public class MaxAddSub {
    public static void main(String[] args) {
        double[] a= {1,-2,3,10,-4,7,2,-5};
        System.out.println(Maxadd(a,0,a.length-1));
    }

    /**
     * 分治法
     * @param a
     * @param from
     * @param to
     * @return
     */
    public static  double Maxadd(double[] a, int from , int to){
        if(from ==to ){
            return a[from];
        }
        int middle = (from + to)/2;
        int i =0;
        double left = a[middle];
        double rigth = a[middle+1];
        double now = a[middle];
        for(i=middle-1;i>=from;i--){
            now += a[i];
            left = Math.max(left,now);
        }
        now = a[middle+1];
        for(i=middle+2;i<=to;i++){
            now += a[i];
            rigth = Math.max(now,rigth);
        }
        double m1= Maxadd(a,from,middle);
        double m2 = Maxadd(a,middle+1,to);
        return Math.max(Math.max(m1,m2),left+rigth);
    }
}
