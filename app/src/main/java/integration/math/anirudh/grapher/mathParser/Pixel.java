package integration.math.anirudh.grapher.mathParser;

import org.apache.commons.math3.complex.Complex;

/**
 * Created by Anirudh on 9/10/2018.
 */

public class Pixel {
    double scale;
    Complex complex;
    int r,c;
    public Pixel(int row, int col, int rows,int cols){
        scale=Math.min(rows,cols)/2;
        double realCol=col-cols/2.0;
        double realRow=rows/2.0-row;
        complex=new Complex(realCol/scale,realRow/scale);
        this.r=row;
        this.c=col;
    }
    public Complex asComplex(){return complex;}
    public int getRow(){return r;}
    public int getCol(){return c;}
    public Pixel(Complex in, int rows,int cols){
        scale=Math.min(rows,cols)/2;
        in=in.multiply(scale);
        c = (int) Math.rint(in.getReal()+cols/2.0);
        r = (int) Math.rint(rows/2.0-in.getImaginary());
    }
}
