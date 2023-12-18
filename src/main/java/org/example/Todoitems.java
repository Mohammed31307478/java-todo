//package org.example;
//
//public class Todoitems {
//    private String taak;
//
//    private boolean completed;
//
//    public Todoitems(String taak, boolean completed) {
//        this.taak = taak;
//        this.completed = completed;
//    }
//
//    public String getTaak() {
//        return taak;
//    }
//
//    public boolean isCompleted() {
//        return completed;
//    }
//
//    public void setCompleted(boolean completed) {
//        this.completed = completed;
//    }
//
//    public void setTaak(String taak) {
//        this.taak = taak;
//    }
//    @Override
//    public String toString() {
//        return taak;
//    }
//}
package org.example;

public class Todoitems implements Comparable<Todoitems> {
    private String id;
    private String taak;
    private boolean completed;

    public Todoitems(String taak, boolean completed) {
        this.taak = taak;
        this.completed = completed;
    }

    public String getTaak() {
        return taak;
    }

        public boolean isCompleted() {
            return completed;
        }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setTaak(String taak) {
        this.taak = taak;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }


    @Override
    public int compareTo(Todoitems other) {
            return this.getTaak().compareTo(other.getTaak());
        }


    @Override
    public String toString() {
        return taak;
    }

}

