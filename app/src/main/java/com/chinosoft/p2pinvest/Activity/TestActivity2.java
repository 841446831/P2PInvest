package com.chinosoft.p2pinvest.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chinosoft.p2pinvest.R;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class TestActivity2 extends AppCompatActivity {

    private StickyListHeadersListView listHeadersListView;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        listHeadersListView = (StickyListHeadersListView) this.findViewById(R.id.stickyListHeadersListViewTest);

        for(int i = 0; i < 20;i++)
        {
            list.add(0+"");
        }
        for(int i = 0; i < 20 ;i++)
        {
            list.add(1+"");
        }
        for(int i = 0; i < 20 ;i++)
        {
            list.add(0+"");
        }
        Log.i("TAG","---->"+list.size());
        MyAdapter myAdapter = new MyAdapter(this,list);
        listHeadersListView.setAdapter(myAdapter);

    }

    class ViewHolder{
        TextView textView;
    }

    class HeaderViewHolder{
        TextView textView;
    }

    public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        private LayoutInflater inflater;
        private List<String> list;

        public MyAdapter(Context context, List<String> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
            Log.i("TAG","---->"+list.size());
        }


        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {

            HeaderViewHolder holder;

            if(convertView == null)
            {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.header,null);
                holder.textView = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            }
            else
            {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            String text = list.get(position).subSequence(0,1).charAt(0) + "";
            holder.textView.setText(text);

            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            return list.get(position).subSequence(0,1).charAt(0);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder;
            if (view == null)
            {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.test_item,null);
                holder.textView = (TextView) view.findViewById(R.id.text);
                view.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(list.get(i));

            return view;
        }
    }

}
