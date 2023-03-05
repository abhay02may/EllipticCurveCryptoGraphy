package com.crypto;

public class ECC {

    // y^2=x^3 + ax +b
    // For Bitcoin a=0 and b=7 (y^2 = x^3 + 7)
    private double a;
    private double b;

    public ECC(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Point pointAddition(Point p,Point q){
        double x1= p.getX();
        double y1= p.getY();

        double x2= q.getX();
        double y2= q.getY();
        double m=0;
        // sometimes we have to do pint doubling
        // If p==q then we need to do pint doubling
        if(Double.compare(x1,x2)==0 && Double.compare(y1,y2)==0){
            m=(3*x1*x1+a)/(2*y1);
        }else {
            m=(y2-y1)/(x2-x1);
        }
        // we can calculate the point R
        double x3=m*m-x2-x1;
        double y3=m*(x1-x3)-y1;
        return new Point(x3,y3);
    }

    public Point doubleAndAdd(int n,Point p){
        Point temp=new Point(p.getX(),p.getY());
        String nBinary=Integer.toBinaryString(n);
        for (int i = 1; i < nBinary.length(); ++i) {
            int actualBit=Integer.parseInt(""+nBinary.charAt(i));
            // pint doubling operation
            temp=pointAddition(temp,temp);
            if(actualBit==1){
                temp=pointAddition(temp,p);
            }
        }
        return temp;
    }
}
