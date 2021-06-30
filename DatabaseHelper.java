package life.saver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.database.FirebaseDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int Database_version= 10;
    private static final String column_id = "id";
    private static final String Database_name = "contact1.db";
    private static final String Table_name = "contacts";
    private static final String Column_name = "name";
    //private static final String column_username = "username";
    private static final String column_password = "password";
    private static final String column_email = "email";
    private static final String column_phone = "phone";
    FirebaseDatabase data;

    SQLiteDatabase db;
    private static final String Table_Create="create table contacts(id integer primary key not null," +
            " name text not null, password text not null, " +
            "email text not null, phone text not null)";
    public DatabaseHelper (Context context){
        super(context, Database_name, null, Database_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_Create);
        this.db=db;
    }
    public void insertcontact(DATABASE c){
       /* db=this.getWritableDatabase();
        ContentValues values=new ContentValues();             

        String quary= "select * from contacts";
        Cursor cursor= db.rawQuery(quary, null);
        int count= cursor.getCount();

        values.put(column_id, count);
        values.put(Column_name,c.getName());
        //values.put(column_username,c.getUsername());
        values.put(column_password,c.getPassword());
        values.put(column_email,c.getEmail());
        values.put(column_phone,c.getPhone());

        db.insert(Table_name, null, values);
        db.close();*/


    }

    public String seacrhpass(String mail)
    {
        db=this.getReadableDatabase();
        String query = "select email,password from contacts";
        Cursor cursor= db.rawQuery(query, null);
        String a,b;
        b= "not found";
        if (cursor.moveToFirst()){
            do {
                a=  cursor.getString(0);
                if(a.equals(mail))
                {
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String email(String email)
    {
        db=this.getReadableDatabase();
        String query = "select email,password from contacts";
        Cursor cursor= db.rawQuery(query, null);
        String a1,b1;
        b1= "1";
        if (cursor.moveToFirst()){
            do {
                a1=  cursor.getString(0);
                if(a1.equals(email))
                {
                    b1="2";
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS contacts";
            db.execSQL(query);
            this.onCreate(db);
        }

}

