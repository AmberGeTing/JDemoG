package bwie.com.jdemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationHelper {
  /**
   * 创建平移动画
   */
  public static Animation createTranslateAnim(Context context, int fromX, int toX) {
    TranslateAnimation tlAnim = new TranslateAnimation(fromX, toX, 0, 0);
    //自动计算时间
    DisplayMetrics dm = new DisplayMetrics();
//获取屏幕信息
    int screenWidth = dm.widthPixels;

    long duration = (long) (Math.abs(toX - fromX) * 1.0f / screenWidth * 4000);
    tlAnim.setDuration(duration);
    tlAnim.setInterpolator(new DecelerateAccelerateInterpolator());
    tlAnim.setFillAfter(true);
    return tlAnim;
  }
}