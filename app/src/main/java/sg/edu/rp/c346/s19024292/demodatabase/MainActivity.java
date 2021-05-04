package sg.edu.rp.c346.s19024292.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView tasksListView;

    ArrayList<Task> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask("Submit RJ", "25 Apr 2006");
                db.close();
            }
        });

        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);

        tasksListView = findViewById(R.id.taskList);

        data = new ArrayList<>();

        ArrayAdapter<Task> adapter = new TaskAdapter(this, R.layout.row_layout, data);
        tasksListView.setAdapter(adapter);

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                data.clear();
                // direct assigning will cause the adapter to reference the wrong instance.
                data.addAll(db.getTaskContent());
                db.close();
                String text = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    text += i + ". " + data.get(i).getDescription() + "\n";
                }
                tvResults.setText(text);
                adapter.notifyDataSetChanged();
            }
        });

    }
}

