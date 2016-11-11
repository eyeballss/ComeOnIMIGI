package me.blog.eyeballs.comeonimigi;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by eye on 16. 11. 10.
 */
public class Crawler extends AsyncTask<Integer, Integer, Integer>{

    private ArrayList<String> resultList;
    private String address;
    private String keyword;

    public Crawler(String address, String keyword) {
        this.address = address;
        this.keyword = keyword;
        resultList = new ArrayList<String>();
    }

    public ArrayList<String> getResults() {
        return resultList;
    }


    private void crawl(){
        try {
            Connection con = Jsoup.connect(address);

            con.timeout(1000 * 15);

            Document document = con.get();
            Elements elements = document.getElementsByTag("img");

            String result;
            for (Element element : elements) {
                result = element.attr("src").toString();
                if(result.contains(keyword)){
                    resultList.add(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        crawl();
        return null;
    }

    protected void onPostExecute(Integer... values){
        System.out.println(resultList.size()+"입니다!!!");
    }
}