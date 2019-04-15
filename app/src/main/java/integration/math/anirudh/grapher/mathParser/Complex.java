//package integration.math.anirudh.grapher.mathParser;
//
///**
// * Created by Anirudh on 9/10/2018.
// */
//
//public class Complex {
//    double real,imaginary;
//    public Complex(){}
//    public Complex(double a, double b){
////        if(rectangular) {
//            real = a;
//            imaginary = b;
////            r = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
////            theta = Math.atan2(imaginary, real);
////        }
////        else{
////            real = a*Math.cos(b);
////            imaginary = a*Math.sin(b);
////            r = a;
////            theta = b;
////        }
//    }
//    public void eat(Complex in){real+=in.real;imaginary+=in.imaginary;}
//
//    public Complex add(Complex in){
//        return new Complex(real+in.real,imaginary+in.imaginary);
//    }
//    public Complex multiply(Complex in){
//        return new Complex(real*in.real-imaginary*in.imaginary,real*in.imaginary+imaginary*in.real);
//    }
//    public static Complex pow(Complex base,int exp){
//        if(exp==0)
//            return new Complex(1,0);
//        if(exp==1)
//            return base;
//        if(exp%2==0)
//            return pow(base.multiply(base),exp/2);
//        else
//            return base.multiply(pow(base,exp-1));
//    }
//
//    public Complex scale(double in){
//        return new Complex(real*in,imaginary*in);
//    }
//
//    public String toString(){
//        if(imaginary==0)
//            return ""+real;
//        if(real==0)
//            return imaginary+"i";
//        if(imaginary<0)
//            return real+""+imaginary+"i";
//        else
//            return real+"+"+imaginary+"i";
//    }
//
//    public Complex pow(double power) {
//            double r = Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
//            double theta = Math.atan2(imaginary, real);
//            r=Math.pow(r,power);
//            theta*=power;
//            real = r*Math.cos(theta);
//            imaginary = r*Math.sin(theta);
//            return new Complex(real,imaginary);
//    }
//}
