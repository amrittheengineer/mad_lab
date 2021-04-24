package com.mad.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.sql.Types.VARCHAR;

public class MainActivity extends AppCompatActivity {

    private EditText empId, name, salary;
    private Button addBtn, modifyBtn, deleteBtn, viewBtn, viewAllBtn;
    private SQLiteDatabase db;
    private static final String EMPLOYEE_DB = "com.madlab.ex4.EMPLOYEE_DB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empId = findViewById(R.id.empId);
        name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);

        addBtn = findViewById(R.id.add_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        modifyBtn = findViewById(R.id.modify_btn);
        viewAllBtn = findViewById(R.id.view_all_btn);
        viewBtn = findViewById(R.id.view_btn);

        db = openOrCreateDatabase(EMPLOYEE_DB, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(empid VARCHAR, name VARCHAR, salary VARCHAR);");


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateAllInputs()){
                    showMessage("Error", "Please enter all details.");
                    return;
                }
                db.execSQL("INSERT INTO employee VALUES('" +
                        getText(empId) + "', '" + getText(name) + "', '" + getText(salary) + "');");
                showMessage("Success", "Record Added");
                clearAll();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValid(empId)){
                    showMessage("Error", "Please enter employee ID.");
                    return;
                }
                db.execSQL("DELETE FROM employee WHERE empId = '" + getText(empId) + "';");
                showMessage("Success", "Record deleted");
                clearAll();
            }
        });


        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateAllInputs()){
                    showMessage("Error", "Please enter all details.");
                    return;
                }
                db.execSQL(
                        "UPDATE employee SET " + "name = '" + getText(name) + "', salary = '" + getText(salary) +
                                "' WHERE empId = '" + getText(empId) + "';");
                showMessage("Success", "Record updated");
                clearAll();
            }
        });


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValid(empId)){
                    showMessage("Error", "Please enter employee ID.");
                    return;
                }
                Cursor cursor = db.rawQuery("SELECT * FROM employee WHERE empId = '" + getText(empId) + "';", null);
                if (cursor.moveToFirst()){
                    name.setText(cursor.getString(1));
                    salary.setText(cursor.getString(2));
                }else{
                    showMessage("Success", "Record not found");
                }
            }
        });


        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.rawQuery("SELECT * FROM employee;", null);
                if (cursor.getCount() > 0){
                    StringBuffer buffer = new StringBuffer();
                    while (cursor.moveToNext()){
                        buffer
                                .append("Employee ID: ")
                                .append(cursor.getString(0))
                                .append("\n")
                                .append("Name: ")
                                .append(cursor.getString(1))
                                .append("\n")
                                .append("Salary: ").append(cursor.getString(2)).append("\n\n");
                    }
                    showMessage("Details", buffer.toString());
                }else{
                    showMessage("Error", "No records found");
                }
            }
        });

    }

    private boolean validateAllInputs(){
        return isValid(empId) && isValid(name) && isValid(salary);
    }

    private boolean isValid(TextView tv){
        return getText(tv) != null;
    }

    private String getText(TextView tv){
        if (tv.getText().toString().trim().equals(""))
            return null;
        return tv.getText().toString().trim();
    }

    private void clearAll(){
        empId.setText("");
        name.setText("");
        salary.setText("");
        empId.requestFocus();
    }

    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true).setTitle(title).setMessage(message).show();
    }
}