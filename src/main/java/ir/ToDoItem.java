package ir;

public class ToDoItem {

    public ToDoItem() {
    }

    public ToDoItem(String name) {
        this.name = name;
        done=0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    private long id;
    private String name;
    private int done;



}