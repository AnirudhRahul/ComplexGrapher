package integration.math.anirudh.grapher;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.fraction.Fraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import integration.math.anirudh.grapher.mathParser.Function;
import integration.math.anirudh.grapher.mathParser.Pixel;
import integration.math.anirudh.grapher.mathParser.Term;

public class MainActivity extends AppCompatActivity {
    public Bitmap getBitmapFromAssets(String fileName) throws IOException {
        AssetManager assetManager = getAssets();

        InputStream istr = assetManager.open(fileName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        return bitmap;
    }
    private static final JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();

    private Bitmap distort(Bitmap myBitmap, Function modifier){
        int height=myBitmap.getHeight();
        int width=myBitmap.getWidth();
        
        Bitmap out=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int[] pixelsIn = new int[height*width];
        ArrayList<Integer>[] pixelsOutList = new ArrayList[height*width];
        myBitmap.getPixels(pixelsIn, 0, width, 0, 0, width, height);
        for(int i=0;i<pixelsOutList.length;i++){
            pixelsOutList[i]=new ArrayList<>();
            int c=i%width;
            int r=i/width;
            Pixel cur=new Pixel(r,c,height,width);
            Complex newLoc=modifier.eval(cur.asComplex());
            Pixel next=new Pixel(newLoc,height,width);
            r=next.getRow();
            c=next.getCol();
//            if(next.getRow()>=height||next.getRow()<0||next.getCol()>=width||next.getCol()<0)
//                continue;
            r%=height;
            c%=width;
            while(c<0)
                c+=width;
            while(r<0)
                r+=width;
            int newIndex=r*width+c;
            pixelsOutList[i].add(pixelsIn[newIndex]);
        }
        int[] pixelsOut=new int[height*width];
        for(int i=0;i<pixelsOutList.length;i++){
            int sum=0;
            for(int e:pixelsOutList[i])
                sum+=e;
            sum/=pixelsOutList[i].size();
            pixelsOut[i]=sum;
        }

        out.setPixels(pixelsOut,0, width, 0, 0, width, height);
        return out;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText functionInput=findViewById(R.id.editText);
        Button refresh=findViewById(R.id.refreshBtn);
        final ImageView distorted=findViewById(R.id.imageViewDistort);
        Switch modeSwitch=findViewById(R.id.modeSwitch);
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function cur=null;
                try {
                    cur = new Function((functionInput.getText().toString() + "+").toCharArray());
                }catch (Exception e){
                    Log.v("error",e.getMessage());
                    Toast.makeText(getApplicationContext(),"There was an error parsing your equation",Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap squareImg = null;
                try {
                    squareImg = getBitmapFromAssets("squares.png");
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"There was an error retrieving the image",Toast.LENGTH_SHORT).show();
                    return;
                }
                distorted.setImageBitmap(distort(squareImg,cur));
            }
        });

    }
}
