package laodresource.demo.com.loadresourcedemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.icon);
        TextView textView = (TextView) findViewById(R.id.text);

        /**
         *  插件apk路径
         */
        String apkPath = Environment.getExternalStorageDirectory()+"/apkbeloaded-debug.apk";
        /**
         *  插件资源对象
         */
        Resources resources = getBundleResource(this,apkPath);
        /**
         *获取图片资源
         */
        Drawable drawable = resources.getDrawable(resources.getIdentifier("icon_be_load", "drawable",
                "laodresource.demo.com.apkbeloaded"));
        /**
         *  获取文本资源
         */
        String text = resources.getString(resources.getIdentifier("text_beload","string",
                "laodresource.demo.com.apkbeloaded"));

        imageView.setImageDrawable(drawable);
        textView.setText(text);


    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            AssetManager.class.getDeclaredMethod("addAssetPath", String.class).invoke(
                    assetManager, apkPath);
            return assetManager;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    public Resources getBundleResource(Context context, String apkPath){
        AssetManager assetManager = createAssetManager(apkPath);
        return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }


}
