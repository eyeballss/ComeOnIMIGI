package me.blog.eyeballs.comeonimigi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    EditText searchKeyword;
    EditText searchAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
    }

    private void init(){
        searchKeyword = (EditText)findViewById(R.id.search_keyword_textview);
        searchAddress = (EditText)findViewById(R.id.search_address_textview);
    }

    public void search(View v){
        switch(v.getId()){
            case R.id.search_button:
                Intent intent = new Intent(this, ImageListActivity.class);
                intent.putExtra("keyword", searchKeyword.getText().toString());
                intent.putExtra("address", searchAddress.getText().toString());
                startActivity(intent);
                break;
        }//switch
    }//search
}
