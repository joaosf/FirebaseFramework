package Modelos;

import java.lang.reflect.Field;
import java.util.HashMap;

import Anotacoes.Fields;
import Anotacoes.FrameworkAnnotation;

public class Registro {
    private String tableName;
    private String keyName = "";
    private HashMap<String, Object> fields;
    private boolean keepSync;

    public Registro(Object object) throws IllegalAccessException {
        fields = new HashMap<String, Object>();

        if (object.getClass().isAnnotationPresent(FrameworkAnnotation.class)) {
            FrameworkAnnotation table = object.getClass().getAnnotation(FrameworkAnnotation.class);
            tableName = table.tableName();
            keepSync = table.keepSync();
        }

        for (Field atributo : object.getClass().getDeclaredFields()) {
            String campo = "";
            if (atributo.isAnnotationPresent(Fields.class)) {
                campo = "";

                Fields field = atributo.getAnnotation(Fields.class);
                campo = field.name();
                if (field.key()) {
                    setKeyName(campo);
                }

                Object value = atributo.get(object);
                fields.put(campo, value);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Get E Set">

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public HashMap<String, Object> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, Object> fields) {
        this.fields = fields;
    }

    public void addField(String name, Object value) {
        this.fields.put(name,value);
    }

    public boolean getKeepSync() {
        return keepSync;
    }

    public void setKeepSync(Boolean keepSync) {
        this.keepSync = keepSync;
    }

    public Object getKeyValue() {
        if (keyName.equals("")) {
            return null;
        }
        return fields.get(keyName);
    }

    // </editor-fold>
}
