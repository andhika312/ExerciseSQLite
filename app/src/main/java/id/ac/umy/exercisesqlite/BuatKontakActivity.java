package id.ac.umy.exercisesqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class BuatKontakActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button simpan;
    EditText nama, noTelp, email, alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_kontak);

        dbHelper = new DataHelper(this);
        nama = (EditText) findViewById(R.id.et_nama);
        noTelp = (EditText) findViewById(R.id.et_noTelp);
        email = (EditText) findViewById(R.id.et_email);
        alamat = (EditText) findViewById(R.id.et_alamat);
        simpan = (Button) findViewById(R.id.btn_simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into kontak(nama, nomor, email, alamat) values('" +
                        nama.getText().toString() + "','" +
                        noTelp.getText().toString() + "','" +
                        email.getText().toString() + "','" +
                        alamat.getText().toString() + "','" +
                        "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                //MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}
