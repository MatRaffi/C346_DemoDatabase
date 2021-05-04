package sg.edu.rp.c346.s19024292.demodatabase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> tasks;
    private Context context;
    private TextView idTextView, descriptionTextView, dateTextView;

    public TaskAdapter(@NonNull Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        Task current = tasks.get(position);

        idTextView = rowView.findViewById(R.id.idTextView);
        descriptionTextView = rowView.findViewById(R.id.descriptionTextView);
        dateTextView = rowView.findViewById(R.id.dateTextView);

        idTextView.setText("" + current.getId());
        descriptionTextView.setText(current.getDescription());
        dateTextView.setText(current.getDate());

        return rowView;
    }
}