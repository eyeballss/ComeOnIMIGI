package me.blog.eyeballs.comeonimigi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ImageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        Intent intent = getIntent();
        String keyword=intent.getExtras().getString("keyword");
        Toast.makeText(getApplicationContext(), keyword, Toast.LENGTH_SHORT).show();
    }
}
