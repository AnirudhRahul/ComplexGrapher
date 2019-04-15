package integration.math.anirudh.grapher.mathParser;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;

/**
 * Created by Anirudh on 9/10/2018.
 */

public class Function {
    ArrayList<Term> list=new ArrayList<>();
    public Function(char[] in){
        int index=-1;
        String cur="";
        boolean negative=false;
        while(++index<in.length){
            if(in[index]=='-') {
                list.add(new Term(cur,negative));
                cur="";
                negative = true;
                continue;
            }
            if(in[index]=='+'){
                list.add(new Term(cur,negative));
                cur="";
                negative=false;
                continue;
            }
            cur+=in[index];
        }

    }
    public Complex eval(Complex in){
        Complex total=new Complex(0,0);
        for(Term e:list){
            total=total.add(e.eval(in));
        }
        return total;
    }
    public String toString(){return list.toString();}
}
