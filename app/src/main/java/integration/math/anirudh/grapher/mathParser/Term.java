package integration.math.anirudh.grapher.mathParser;

import org.apache.commons.math3.complex.Complex;

/**
 * Created by Anirudh on 9/10/2018.
 */

public class Term {
    boolean negative;
    String in;
    double power;
    double coefficient;
    public Term(String in, boolean negative){
        this.in=in;
        this.negative=negative;
        if(!in.contains("z")){
            power=0;
            coefficient=Double.parseDouble(in);
        }
        else {
            int expIndex = in.lastIndexOf("^");
            if (expIndex == -1)
                power = 1;
            else
                power = Double.parseDouble(in.substring(expIndex + 1, in.length()));

            if (in.startsWith("z"))
                coefficient = 1;
            else
                coefficient = Double.parseDouble(in.substring(0, in.indexOf("z")));
        }
        if(negative)
            coefficient*=-1;
    }

    public Complex eval(Complex in){

        if(power==0)
            return new Complex(coefficient,0);
        else
            return in.pow(power).multiply(coefficient);
    }

    public String toString(){return negative?"-":"+"+in;}
}
