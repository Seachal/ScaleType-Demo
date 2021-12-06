package org.tbadg.scaletypedemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    RecyclerView recycler_view;

    private final int RESULT_CODE_1 = 100;
    private long exitTime = 0;


    private List<StartActivityBean> activityList = new ArrayList<StartActivityBean>();


    {
        activityList.add(new StartActivityBean("可选择", MainActivity.class));
        activityList.add(new StartActivityBean("SmallActivity", SmallActivity.class));

        activityList.add(new StartActivityBean("WideActivity", WideActivity.class));

        activityList.add(new StartActivityBean("TallActivity", TallActivity.class));

        activityList.add(new StartActivityBean("AllActivity", AllActivity.class));
        activityList.add(new StartActivityBean("AllActivity AdjustViewBounds", AllAndAdjustViewBoundsActivity.class));
        activityList.add(new StartActivityBean("Constraint Imageview AdjustViewBounds", ConstraintImageviewActivity.class));

        activityList.add(new StartActivityBean("Constraint Imageview AdjustViewBounds2 ", ConstraintImageviewActivity2.class));
        activityList.add(new StartActivityBean("Relative ImageviewAdjustViewBounds2 ", RelativeImageviewActivity.class));



        activityList.add(new StartActivityBean("ConstraintImageviewWrapNormalTallActivity_test", ConstraintImageviewWrapNormalTallActivity_test.class));

        activityList.add(new StartActivityBean("ConstraintImageviewWrapNormalTallActivity1", ConstraintImageviewWrapNormalTallActivity1.class));
        activityList.add(new StartActivityBean("ConstraintImageviewWrapNormalTallActivity2", ConstraintImageviewWrapNormalTallActivity2.class));
        activityList.add(new StartActivityBean("ConstraintImageviewWrapNormalTallActivity3", ConstraintImageviewWrapNormalTallActivity3.class));





    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setAdapter(new MyAdapter(ListActivity.this, activityList));
        recycler_view.setLayoutManager(new LinearLayoutManager(ListActivity.this));




    }


    // 双击退出
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private Context context;
        private List<StartActivityBean> arrayList;

        public MyAdapter(Context context, List<StartActivityBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,
                    false));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.mTextView.setText(arrayList.get(position).getTitle());
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListActivity.this, arrayList.get(position).getActivityClass());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return activityList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout llroot;
            private TextView mTextView;


            public MyViewHolder(View itemView) {
                super(itemView);
                llroot = itemView.findViewById(R.id.ll_root);
                mTextView = itemView.findViewById(R.id.tv_items);

            }
        }
    }


}
