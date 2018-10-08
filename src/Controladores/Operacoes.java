package Controladores;

import Singleton.FrameworkLogger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import Modelos.Filtro;
import Modelos.Registro;

public class Operacoes {
    // <editor-fold defaultstate="collapsed" desc="Select">

    private static void Generator(final Registro registro) {
        Query q = Base.getGenerator(registro.getTableName());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int generator;
                try {
                    generator = Integer.parseInt(dataSnapshot.child(registro.getTableName()).getValue(String.class))+1;
                } catch (Exception e) {
                    generator = 1;
                }

                HashMap<String, Object> map = registro.getFields();
                map.put(registro.getKeyName(),generator);
                registro.setFields(map);

                Update(registro);
                Base.getTableReference("generator",false).child(registro.getTableName()).setValue(generator+"",null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                FrameworkLogger.getInstance().addLog("FirebaseFramework",databaseError.getMessage());
            }
        });
    }

    public static Query Select(String tableName, Filtro filtro) {
        Query q = Base.getQuery(tableName,filtro);
        return q;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Insert/Update">

    private static void Update(Registro registro) {
        DatabaseReference myRef = Base.getTableReference(registro.getTableName(), registro.getKeepSync());
        HashMap<String,Object> fields = registro.getFields();

        if (registro.getKeyValue() == null) {
            myRef.push().setValue(fields,null);
        } else {
            String key;
            String value;
            for (HashMap.Entry item : fields.entrySet()) {
                key = item.getKey().toString();
                value = item.getValue().toString();
                myRef.child(registro.getKeyValue().toString()).child(key).setValue(value,null);
            }
        }
    }

    public static void Update(Object object) {
        try{
            Registro registro = new Registro(object);
            Update(registro);
        } catch (Exception e) {
            FrameworkLogger.getInstance().addLog("FirebaseFramework",e.getMessage());
        }
    }

    public static void InsertOrUpdate(Object object) {
        try{
            Registro registro = new Registro(object);
            if (registro.getKeyValue() instanceof Integer) {
                if (Integer.parseInt(registro.getKeyValue().toString()) == 0 || Integer.parseInt(registro.getKeyValue().toString()) == 1) {
                    Generator(registro);
                    return;
                }
            }
            Update(registro);
        } catch (Exception e) {
            FrameworkLogger.getInstance().addLog("FirebaseFramework",e.getMessage());
        }
    }

    public static void InsertOrUpdate(String child, Object object, Boolean withPush) {
        try {
            DatabaseReference myRef = Base.getTableReference(child, false);
            if (withPush) {
                myRef.push().setValue(object,null);
            } else {
                myRef.setValue(object,null);
            }

        } catch (Exception e) {
            FrameworkLogger.getInstance().addLog("FirebaseFramework",e.getMessage());
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Delete">

    public static void Delete(Object object) {
        try {
            Registro registro = new Registro(object);
            DatabaseReference myRef = Base.getTableReference(registro.getTableName(), false);
            myRef.child(registro.getKeyValue().toString()).setValue(null,null);
        } catch (Exception e) {
            FrameworkLogger.getInstance().addLog("FirebaseFramework",e.getMessage());
        }
    }

    // </editor-fold>
}
