package anhduy.dmt.doanhduy_note;

import java.io.Serializable;

public class Note implements Serializable, Comparable<Note> {
    int id;
    String tilte;
    String content;
    String time;

    public Note(int id, String tilte, String content, String time) {
        this.id = id;
        this.tilte = tilte;
        this.content = content;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int compareTo(Note note) {
        if(tilte.compareToIgnoreCase(note.tilte) == 0){
            return content.compareToIgnoreCase(note.content);
        }
        return tilte.compareToIgnoreCase(note.tilte);
    }
}
