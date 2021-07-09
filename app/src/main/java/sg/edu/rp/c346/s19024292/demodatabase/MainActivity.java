package sg.edu.rp.c346.s19024292.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView tasksListView;
    EditText etTask,etDate;
    ArrayList<Task> data;
    ToggleButton tgSort;
    Boolean toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etTask = findViewById(R.id.etText);
        etDate = findViewById(R.id.etText2);
        tgSort = findViewById(R.id.toggleSort);

        tgSort.setChecked(true);

        tasksListView = findViewById(R.id.taskList);
        data = new ArrayList<>();

        ArrayAdapter<Task> adapter = new TaskAdapter(this, R.layout.row_layout, data);
        tasksListView.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = etTask.getText().toString();
                String date = etDate.getText().toString();

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(task, date);
                adapter.notifyDataSetChanged();
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the DBHelper object, passing in the activity's content
                DBHelper db = new DBHelper(MainActivity.this);

                // insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
            }
        });

    }

}

