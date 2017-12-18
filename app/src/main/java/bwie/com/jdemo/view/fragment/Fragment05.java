package bwie.com.jdemo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.com.jdemo.R;
import bwie.com.jdemo.utils.OkHttp;
import bwie.com.jdemo.view.LoginActivity;
import bwie.com.jdemo.view.XiaoXiActivity;
import okhttp3.Request;

/**
 * Created by ASUS on 2017/12/4.
 */

public class Fragment05 extends Fragment {
    private View view;
    /**
     * 登录/注册 >
     */
    private TextView mLogin;
    private ImageView mWei;
    private ImageView mZhi;
    private ImageView mXiaoxi;
    private String state = "";
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private ImageView mTou;
    private LinearLayout mMyDing;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在创建视图的时候,先清空数据库
        SharedPreferences sp = getContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment05, container, false);

        initView(view);
        //点击登录--跳转
        //点击跳转到登录注册的界面
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //点击消息
        mXiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断用户是否登录--如果登录--跳转到消息界面
                //如果没有登录--跳转到登陆界面
                //查询数据库
                SharedPreferences sp = getContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE); //取得user_id和手机号 numbers = sp.getString("user_mobile", "");//如果取不到值就取后面的""
                state = sp.getString("state", "0");
                //判断
                if ("登录".equals(state)) {
                    //跳转到消息界面
                    Intent intent2 = new Intent(getActivity(), XiaoXiActivity.class);
                    startActivity(intent2);
                } else {
                    //跳转到登录界面
                    Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent3);
                }
            }
        });
        mTou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 激活系统图库，选择一张图片
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
            }
        });
        SharedPreferences sp = getContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE); //取得user_id和手机号 numbers = sp.getString("user_mobile", "");//如果取不到值就取后面的""
        String name = sp.getString("name", "登录/注册");
        //设置值
        mLogin.setText(name);
        //点击我的订单
        mMyDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询订单信息

            }
        });
        return view;
    }

    /*
  * 判断sdcard是否被挂载
  */
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                /**
                 * 获得图片
                 */
                mTou.setImageBitmap(bitmap);
                //保存到SharedPreferences
                saveBitmapToSharedPreferences(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //保存图片到SharedPreferences
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("testSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();

        //上传头像
        setImgByStr(imageString, "");
    }


    /**
     * 上传头像
     *
     * @param imgStr
     * @param imgName
     */
    public void setImgByStr(String imgStr, String imgName) {
        String url = "http://appserver.1035.mobi/MobiSoft/User_upLogo";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "11460047");// 11459832
        params.put("data", imgStr);
        OkHttp.postAsync(url, params, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.i("上传失败", "失败" + request.toString() + e.toString());
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.i("上传成功", result);
            }
        });
    }

    //从SharedPreferences获取图片
    private void getBitmapFromSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("testSP", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString = sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {
            mTou.setImageResource(R.mipmap.ic_launcher);
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            mTou.setImageBitmap(bitmap);
        }

    }


    private void initView(View view) {
        mLogin = (TextView) view.findViewById(R.id.login);
        mWei = (ImageView) view.findViewById(R.id.wei);

        mZhi = (ImageView) view.findViewById(R.id.zhi);
        mXiaoxi = (ImageView) view.findViewById(R.id.xiaoxi);
        mTou = (ImageView) view.findViewById(R.id.tou);
        mMyDing = (LinearLayout) view.findViewById(R.id.myDing);
    }


}
