package com.itheima.day28;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        //下载图片
        //1.确定网址
        String path = "http://192.168.16.102:8080/dd.jpg";
        try {
            //2.把网址封装成一个url对象
            URL url = new URL(path);
            //3.获取连接对象，此时还没有建立连接，没有产生任何的网络交互
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //4.对连接对象进行初始化
            //设置请求方法，注意大写
            conn.setRequestMethod("GET");
            //设置连接超时
            conn.setConnectTimeout(5000);
            //设置读取超时
            conn.setReadTimeout(5000);
            //5.发送请求，与服务器建立连接
            conn.connect();
            //如果响应码为200，说明请求成功
            if (conn.getResponseCode() == 200) {
                //获取服务器响应头中的流，流里的数据就是客户端请求的数据
                InputStream is = conn.getInputStream();
                //读取出流里的数据，并构造成位图对象
                Bitmap bm = BitmapFactory.decodeStream(is);

                ImageView iv = (ImageView) findViewById(R.id.iv);
                //把位图对象显示至ImageView
                iv.setImageBitmap(bm);
            } else {
                Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
