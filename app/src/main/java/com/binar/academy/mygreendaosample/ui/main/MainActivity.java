package com.binar.academy.mygreendaosample.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.binar.academy.mygreendaosample.MainApp;
import com.binar.academy.mygreendaosample.R;
import com.binar.academy.mygreendaosample.database.entity.DaoSession;
import com.binar.academy.mygreendaosample.database.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentDialog.StudentDialogCallback,
        MainAdapter.ItemListener {

    private MainAdapter adapter;
    private RecyclerView rvStudents;
    private Button btnAdd;
    private StudentDialog studentDialog;

    private DaoSession daoSession;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialogFragment(true);
            }
        });

        adapter = new MainAdapter(MainActivity.this, this);

        rvStudents = findViewById(R.id.rv_students);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.setAdapter(adapter);

        daoSession = ((MainApp) getApplication()).getDaoSession(); // akses singleton

        studentList = new ArrayList<>();
        studentList = daoSession.getStudentDao().loadAll(); //memuat database

        adapter.updateStudentList(studentList);
    }

    @Override
    public void onSaveClick(Student student) {
        daoSession.getStudentDao().insert(student); //menambahkan data
        adapter.updateStudentList(
                daoSession.getStudentDao().loadAll()
        );
    }

    @Override
    public void onDeleteClick(Long id) {
        for (Student student : daoSession.getStudentDao().loadAll()) {
            if (id.equals(student.getId())) {
                daoSession.getStudentDao().delete(student); //menghapus data
                adapter.updateStudentList(
                        daoSession.getStudentDao().loadAll()
                );
                break;
            }
        }
    }

    @Override
    public void onUpdateClick(Long id, String name, String gender) {
        for (Student student : daoSession.getStudentDao().loadAll()) {
            if (id.equals(student.getId())) {
                student.setNama(name);
                student.setGender(gender);
                daoSession.getStudentDao().update(student); //modify data
                adapter.updateStudentList(
                        daoSession.getStudentDao().loadAll()
                );
                break;
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Student student = adapter.getStudentList().get(position);
        studentDialog = StudentDialog.newInstance(false, student.getId(), student.getNama(), student.getGender());
        studentDialog.setStudentDialogCallback(this);

        if (!studentDialog.isVisible()) {
            studentDialog.show(getFragmentManager(), studentDialog.getTag());
        }
    }

    private void showDialogFragment(boolean isAdd) {
        studentDialog = StudentDialog.newInstance(isAdd, null, "", "");
        studentDialog.setStudentDialogCallback(this);

        if (!studentDialog.isVisible()) {
            studentDialog.show(getFragmentManager(), studentDialog.getTag());
        }
    }
}
