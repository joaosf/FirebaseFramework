package Controladores;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import Modelos.Filtro;
import Modelos.Registro;

public class Base {
    public static DatabaseReference getTableReference(String sTable, boolean bManterOffline) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(sTable);
        myRef.keepSynced(bManterOffline);
        return myRef;
    }

    public static Query getQuery(String tableName, Filtro filtro) {
        DatabaseReference myRef = getTableReference(tableName, true);
        if (!filtro.getValue().toString().equals("")) {
            return myRef.orderByChild(filtro.getName()).equalTo(filtro.getValue().toString());
        } else {
            return myRef.orderByChild(filtro.getName());
        }
    }

    public static Query getGenerator(String tableName) {
        DatabaseReference myRef = getTableReference("generator", false);
        return myRef.orderByChild(tableName);
    }
}
