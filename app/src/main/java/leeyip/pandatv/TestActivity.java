package leeyip.pandatv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import leeyip.pandatv.weiget.tabviewpager.TabViewPager;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TabViewPager tabViewPager = (TabViewPager) findViewById(R.id.tabViewPager);
        tabViewPager.setup(new String[]{"A", "B", "C"},
                View.inflate(this, R.layout.layout_header, null),
                false,
                new Function2<RecyclerView, Integer, Unit>() {
                    @Override
                    public Unit invoke(RecyclerView recyclerView, Integer integer) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(TestActivity.this));
                        recyclerView.setAdapter(new MyAdapter());
                        return null;
                    }
                });
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }
        class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
