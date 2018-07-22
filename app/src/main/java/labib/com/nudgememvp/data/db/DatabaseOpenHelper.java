package labib.com.nudgememvp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import labib.com.nudgememvp.Logy;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String Database_Name = "notices.db";
    private static final String Table_Name = "notices_table";

    public static final String Col_1 = "id";
    public static final String Col_2 = "message";
    public static final String Col_3 = "place_title";
    public static final String Col_4 = "place_lat";
    public static final String Col_5 = "place_lon";
    public static final String Col_6 = "time";

    public DatabaseOpenHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTICES_TABLE = "CREATE TABLE " + Table_Name + "( " +
                Col_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Col_2 + "  TEXT, " +
                Col_3 + "  TEXT, " +
                Col_4 + "  REAL, " +
                Col_5 + "  REAL, " +
                Col_6 + "  INTEGER );";

        db.execSQL(CREATE_NOTICES_TABLE);
        new Logy("db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Table_Name;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


    // add new nudge
    long newNudge(String message, String placeTitle, double longitude, double latitude) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, message);
        contentValues.put(Col_3, placeTitle);
        contentValues.put(Col_4, longitude);
        contentValues.put(Col_5, latitude);
        contentValues.put(Col_6, System.currentTimeMillis());

        return db.insert(Table_Name, null, contentValues);

    }

    // delete all nudges
    void clearAll() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Name, null, null);
    }

    // delete particular notice
    public void deleteOne(long id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = Col_1 + "= ?";
        String[] args = new String[]{String.valueOf(id)};
        db.delete(Table_Name, where, args);

        new Logy(db.delete(Table_Name, where, args));
    }

    // get all notices
    ArrayList<Nudge> getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Table_Name,
                null, null, null, null, null, null);

        ArrayList<Nudge> nudges = new ArrayList<>();
        while (cursor.moveToNext()) {
            nudges.add(new Nudge(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getLong(5)));
        }
        return nudges;
    }
}
