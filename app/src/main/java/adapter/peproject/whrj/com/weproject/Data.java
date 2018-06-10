package adapter.peproject.whrj.com.weproject;

public class Data {
    private String mingzi;
    private String baoming;
    private int tubiao;
    private String daxiao;

    public Data(String mingzi,String baoming,String daxiao,int tubiao)
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

    public int getTubiao() {
        return tubiao;
    }

    public String getDaxiao() {
        return daxiao;
    }


}
