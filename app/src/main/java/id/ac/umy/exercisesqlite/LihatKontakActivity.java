package id.ac.umy.exercisesqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatKontakActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView nama, notelp, email, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kontak);
        dbHelper = new DataHelper(this);
        nama = (TextView) findViewById(R.id.et_nama);
        notelp = (TextView) findViewById(R.id.et_noTelp);
        email = (TextView) findViewById(R.id.et_email);
        alamat = (TextView) findViewById(R.id.et_alamat);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM Contact WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            notelp.setText(cursor.getString(1).toString());
            email.setText(cursor.getString(2).toString());
            alamat.setText(cursor.getString(3).toString());
        }
    }
}
