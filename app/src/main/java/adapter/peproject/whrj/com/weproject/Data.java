package adapter.peproject.whrj.com.weproject;

import android.graphics.drawable.Drawable;

public class Data {
    public String mingzi;
    public String baoming;
    public Drawable tubiao;
    public String daxiao;

    public Data()
    {

    }
    public Data(String mingzi,String baoming,String daxiao,Drawable tubiao)
    {
           this.baoming=baoming;
           this.mingzi=mingzi;
           this.daxiao=daxiao;
           this.tubiao=tubiao;
    }
    public String getMingzi() {
        return mingzi;
    }

    public String getBaoming() {
        return baoming;
    }

    public Drawable getTubiao() {
        return tubiao;
    }

    public String getDaxiao() {
        return daxiao;
    }


}
