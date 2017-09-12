package com.binar.academy.mygreendaosample.ui.main;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.binar.academy.mygreendaosample.R;
import com.binar.academy.mygreendaosample.database.entity.Student;

/**
 * Created by herisulistiyanto on 9/12/17.
 */

public class StudentDialog extends DialogFragment {

    private Button btnSave, btnDelete, btnUpdate;
    private EditText etNama, etGender;
    private StudentDialogCallback studentDialogCallback;
    private Long id;

    public static StudentDialog newInstance(boolean isAdd, Long id, String nama, String gender) {

        Bundle args = new Bundle();
        args.putBoolean(IntentKey.IS_ADD, isAdd);
        if (null != id) {
            args.putLong(IntentKey.STUDENT_ID, id);
        }
        args.putString(IntentKey.STUDENT_NAME, nama);
        args.putString(IntentKey.STUDENT_GENDER, gender);
        StudentDialog fragment = new StudentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public void setStudentDialogCallback(StudentDialogCallback studentDialogCallback) {
        this.studentDialogCallback = studentDialogCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        btnSave = rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDialogCallback.onSaveClick(
                        new Student(
                                null,
                                etNama.getText().toString(),
                                etGender.getText().toString()
                        )
                );
                dismiss();
            }
        });
        btnDelete = rootView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDialogCallback.onDeleteClick(id);
                dismiss();
            }
        });

        btnUpdate = rootView.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDialogCallback.onUpdateClick(
                        id,
                        etNama.getText().toString(),
                        etGender.getText().toString()
                );
                dismiss();
            }
        });
        etNama = rootView.findViewById(R.id.et_nama);
        etGender = rootView.findViewById(R.id.et_gender);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (null != bundle) {
            String name = bundle.getString(IntentKey.STUDENT_NAME, "");
            etNama.setText(name);

            String gender = bundle.getString(IntentKey.STUDENT_GENDER, "");
            etGender.setText(gender);

            boolean isAdd = bundle.getBoolean(IntentKey.IS_ADD, false);

            btnDelete.setVisibility(
                    (isAdd) ? View.GONE : View.VISIBLE
            );

            btnUpdate.setVisibility(
                    (isAdd) ? View.GONE : View.VISIBLE
            );

            btnSave.setVisibility(
                    (isAdd) ? View.VISIBLE : View.GONE
            );

            Long _id = bundle.getLong(IntentKey.STUDENT_ID);
            if (null != _id) {
                this.id = _id;
            }
        }
    }

    public interface StudentDialogCallback {
        void onSaveClick(Student student);

        void onUpdateClick(Long id, String name, String gender);

        void onDeleteClick(Long id);
    }


    public final class IntentKey {
        private IntentKey() {
        }

        public static final String IS_ADD = "StudentDialog.IS_ADD";
        public static final String STUDENT_ID = "StudentDialog.STUDENT_ID";
        public static final String STUDENT_NAME = "StudentDialog.STUDENT_NAME";
        public static final String STUDENT_GENDER = "StudentDialog.STUDENT_GENDER";
    }

}
