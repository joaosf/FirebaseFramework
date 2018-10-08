package Modelos;

public class Filtro {
    private String name;
    private Object value;

    public Filtro(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    // <editor-fold defaultstate="collapsed" desc="Get E Set">

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    // </editor-fold>
}
