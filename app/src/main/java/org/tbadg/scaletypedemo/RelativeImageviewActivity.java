package org.tbadg.scaletypedemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 * 这个案例让我发现一个问题，   relativelayout 中有多个控件时，     让他们实现  constraint中的哪种居中是需要借助
 * 其他 view 来实现的，  这样就可能会增加嵌套的层级。
 *
 * [(63条消息) RelativeLayout中两个控件怎么居中显示？_AiShengHuo的博客-CSDN博客](https://blog.csdn.net/qq_35956194/article/details/77413706)
 */
public class RelativeImageviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_imageview);
    }
}