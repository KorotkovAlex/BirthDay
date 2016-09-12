package brain.birthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Item> result =new ArrayList<Item>();
    private ContactAdapter contactAdapter;
    private DBHelper2 dbHelper;
    private ItemDAO itemDAO ;
    private LinearLayout linearLayout;
    static final private int CHOOSE_THIEF = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper2(this);
        itemDAO = new ItemDAO(dbHelper);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            contactAdapter = new ContactAdapter(itemDAO.findAll(), dbHelper);
            recyclerView.setAdapter(contactAdapter);

            ItemTouchHelper.Callback callback = new MovieTouchHelper(contactAdapter);
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(recyclerView);
        // При добавлении открывается новый активити
        linearLayout =(LinearLayout)findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionIntent = new Intent(MainActivity.this,SQLActivity.class);
                startActivityForResult(questionIntent, CHOOSE_THIEF);
            }
        });



    }

}
