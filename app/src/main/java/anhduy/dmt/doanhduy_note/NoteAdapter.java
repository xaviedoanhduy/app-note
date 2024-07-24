package anhduy.dmt.doanhduy_note;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> implements Filterable {
    ArrayList<Note> notes;
    ArrayList<Note> noteFilter;
    Listener listener;
    public NoteAdapter(ArrayList<Note> notes, Listener listener) {
        this.notes = notes;
        this.listener = listener;
        this.noteFilter=notes;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent,false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH holder,int position) {
        Note note = noteFilter.get(position);
        holder.titleOutput.setText(note.getTilte());
        holder.contentOutput.setText(note.getContent());
        holder.timeOutput.setText(note.getTime());
//        String formatDate = DateFormat.getDateTimeInstance().format(note.time);
//        holder.timeOutput.setText(formatDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemListener(position,note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if(charString.isEmpty()) noteFilter=notes;
                else {
                    List<Note> filteredList = new ArrayList<>();
                    for (Note row : notes) {
                        if(row.getTilte().toLowerCase().contains(charString.toLowerCase()) || row.getContent().contains(charSequence) || row.getTime().contains(charSequence)){
                            filteredList.add(row);
                        }
                    }
                    noteFilter = (ArrayList<Note>) filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = noteFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                noteFilter= (ArrayList<Note>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class NoteVH extends RecyclerView.ViewHolder{
        TextView titleOutput, contentOutput, timeOutput;

        public NoteVH(@NonNull View itemView) {
            super(itemView);
            titleOutput=itemView.findViewById(R.id.titleOuput);
            contentOutput=itemView.findViewById(R.id.contentOuput);
            timeOutput=itemView.findViewById(R.id.timeOuput);
        }
    }

//    public void addNote(Note note){
//        noteFilter.add(note);
//        notifyDataSetChanged();
//    }
//    public void editNote(Note note, int pos){
//        noteFilter.set(pos, note);
//        notifyDataSetChanged();
//    }
//    public void deleteNote(Note note) {
//        noteFilter.remove(note);
//        notifyDataSetChanged();
//    }
    interface Listener{
        void OnItemListener(int pos, Note note);
    }
}
