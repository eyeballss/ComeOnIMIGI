package me.blog.eyeballs.comeonimigi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    EditText searchKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchKeyword = (EditText)findViewById(R.id.searchKeyword);
    }

    public void search(View v){
        switch(v.getId()){
            case R.id.searchButton:
                Intent intent = new Intent(this, ImageListActivity.class);
                intent.putExtra("keyword", searchKeyword.getText().toString());
                startActivity(intent);
                break;
        }//switch
    }//search
}
