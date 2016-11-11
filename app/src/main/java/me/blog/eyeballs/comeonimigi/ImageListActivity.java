package me.blog.eyeballs.comeonimigi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import me.blog.eyeballs.comeonimigi.image_list.Adapter;
import me.blog.eyeballs.comeonimigi.image_list.SwipeDismissListViewTouchListener;

public class ImageListActivity extends AppCompatActivity {

    private String keyword;
    private String address;
    private ArrayList<String> imageArray;
    private Crawl crawl;
    private ListView image_listview;
    private Adapter adapter;
    private ProgressBar image_loading_progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        init();
        setSwipeToDismiss();

        //crawl!
        crawl.execute();
    }

    private void setSwipeToDismiss() {
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        image_listview,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    removeData(position);

                                }
                                adapter.notifyDataSetChanged();
                            }
                        });//touchListener

        image_listview.setOnTouchListener(touchListener);
        image_listview.setOnScrollListener(touchListener.makeScrollListener());
    }

    private void removeData(int i){
        adapter.remove(i);
    }

    private void init(){
        Intent intent = getIntent();
        keyword =intent.getExtras().getString("keyword");
        address =intent.getExtras().getString("address");

        imageArray = new ArrayList<String>();

        crawl = new Crawl();

        image_listview = (ListView)findViewById(R.id.image_listview);
        adapter = new Adapter(this, imageArray);
        image_listview.setAdapter(adapter);

        image_loading_progressbar= (ProgressBar)findViewById(R.id.image_loading_progressbar);
    }


    private class Crawl extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Connection con = Jsoup.connect(address);

                con.timeout(1000 * 15);

                Document document = con.get();
                Elements elements = document.getElementsByTag("img");

                String result = null;
                for (Element element : elements) {
                    result = element.attr("src").toString();
                    if(result.contains(".jp") || result.contains(".JP") || result.contains(".bmp") || result.contains(".BMP")){
                        imageArray.add(result);
                    }
                    if(!keyword.equals("") && result.contains(keyword)){
                        imageArray.add(result);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "done with crawling!", Toast.LENGTH_SHORT).show();
            image_loading_progressbar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }
}
