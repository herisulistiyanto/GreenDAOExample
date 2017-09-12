package com.binar.academy.mygreendaosample.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binar.academy.mygreendaosample.R;
import com.binar.academy.mygreendaosample.database.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herisulistiyanto on 9/12/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Student> studentList;
    private Context context;
    private ItemListener itemListener;

    public MainAdapter(Context context, ItemListener listener) {
        this.context = context;
        studentList = new ArrayList<>();
        itemListener = listener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new MainViewHolder(rootView, itemListener);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        if (null != studentList) {
            String nama = studentList.get(position).getNama();
            String gender = studentList.get(position).getGender();
            holder.tvNama.setText(nama);
            holder.tvGender.setText(gender);
        }
    }

    @Override
    public int getItemCount() {
        return (null != studentList) ? studentList.size() : 0;
    }

    public void updateStudentList(List<Student> studentList) {
        this.studentList.clear();
        this.studentList.addAll(studentList);
        notifyDataSetChanged();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View rootView;
        private TextView tvNama;
        private TextView tvGender;

        private ItemListener itemListener;

        public MainViewHolder(View itemView, final ItemListener itemListener) {
            super(itemView);
            this.rootView = itemView;
            tvNama = rootView.findViewById(R.id.tv_nama);
            tvGender = rootView.findViewById(R.id.tv_gender);
            rootView.setOnClickListener(this);
            this.itemListener = itemListener;
        }

        @Override
        public void onClick(View view) {
            itemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemListener {
        void onItemClick(int position);
    }
}
