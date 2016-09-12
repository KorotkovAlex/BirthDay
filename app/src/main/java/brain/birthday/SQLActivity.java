package brain.birthday;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private DBHelper2 dbHelper;
    private ItemDAO itemDAO;
    private EditText editTextName;
    private EditText editTextDay;
    private EditText editTextMo;
    private EditText editTextYe;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        dbHelper = new DBHelper2(this);
        itemDAO = new ItemDAO(dbHelper);
        editTextName = (EditText) findViewById(R.id.editText);
        editTextDay = (EditText) findViewById(R.id.editTextD);
        editTextMo = (EditText) findViewById(R.id.editTextM);
        editTextYe = (EditText) findViewById(R.id.editTextY);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textName = editTextName.getText().toString().trim();
                String editTextD =  editTextDay.getText().toString().trim();
                String editTextM = editTextMo.getText().toString().trim();
                String editTextY = editTextYe.getText().toString().trim();


                if (!textName.isEmpty() && !editTextD.isEmpty() && !editTextM.isEmpty() && !editTextY.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String date1 = "" + editTextD + "-" + editTextM + "-" + editTextY;
                    try {
                        //The code you are trying to exception handle
                        Date date2 = sdf.parse(date1);
                        Log.d("ss", "" + date2.getTime());
                        Item item = new Item();
                        item.date = "" + date2.getTime();
                        item.name = textName;
                        itemDAO.persist(item);
                        Intent questionIntent = new Intent(SQLActivity.this, MainActivity.class);
                        startActivityForResult(questionIntent, 0);
                    }catch (Exception e) {
                        //The handling for the code
                        e.printStackTrace();
                    }



                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SQL Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://brain.birthday/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SQL Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://brain.birthday/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
