package wb.app.seek.common.base;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.squareup.leakcanary.LeakCanary;

import wb.app.library.MLog;
import wb.app.seek.BuildConfig;
import wb.app.seek.common.utils.SPKey;

/**
 * Created by W.b on 2016/12/29.
 */
public class BaseApplication extends Application {

  private static BaseApplication mInstance;
  private SeekHelper mHelper;

  @Override
  public void onCreate() {
    super.onCreate();

    mInstance = this;

    mHelper = SeekHelper.getInstance();

    MLog.init(BuildConfig.DEBUG);

    int uiMode = getHelper().getSpUtils().getInt(SPKey.UI_MODE);
    if (uiMode == AppCompatDelegate.MODE_NIGHT_NO) {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    } else if (uiMode == AppCompatDelegate.MODE_NIGHT_YES) {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    //初始化内存泄露检测
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
  }

  public static BaseApplication getInstance() {
    return mInstance;
  }

  public SeekHelper getHelper() {
    return mHelper;
  }
}
