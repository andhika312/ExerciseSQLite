package id.ac.umy.exercisesqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateKontakActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button batal, update;
    EditText nama, notelp, email, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kontak);

        dbHelper = new DataHelper(this);
        nama = (EditText) findViewById(R.id.et_nama);
        notelp = (EditText) findViewById(R.id.et_noTelp);
        email = (EditText) findViewById(R.id.et_email);
        alamat = (EditText) findViewById(R.id.et_alamat);
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
        update = (Button) findViewById(R.id.btn_ubah);
        batal = (Button) findViewById(R.id.btn_batal);
        // daftarkan even onClick pada btnSimpan
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update Contact set nama='"+
                        nama.getText().toString() +"', noTelp='" +
                        notelp.getText().toString()+"', email='"+
                        email.getText().toString() +"', alamat='" +
                        alamat.getText().toString() + "' where nama='" +
                        nama.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
