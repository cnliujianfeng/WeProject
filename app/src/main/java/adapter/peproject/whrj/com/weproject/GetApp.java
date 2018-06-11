package adapter.peproject.whrj.com.weproject;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;

import junit.runner.Version;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetApp {

 public static ArrayList<Data> getAppInfo(Context ctx)
 {
     ArrayList<Data> appList=new ArrayList<>();
     SparseIntArray siArray=new SparseIntArray();
     PackageManager pm=ctx.getPackageManager();
     List<ApplicationInfo> installList = pm.getInstalledApplications(
             PackageManager.PERMISSION_GRANTED);
     for (int i = 0; i < installList.size(); i++) {
         ApplicationInfo item = installList.get(i);
         Data dat=new Data();
        dat.mingzi=item.loadLabel(pm).toString();
        dat.baoming=item.packageName;
        dat.tubiao=item.loadIcon(pm);
         try {
             PackageInfo packinfo = pm.getPackageInfo(dat.baoming, 0);
             dat.daxiao=packinfo.versionName;
         } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
         }


         appList.add(dat);
     }
     return appList;
 }



}
