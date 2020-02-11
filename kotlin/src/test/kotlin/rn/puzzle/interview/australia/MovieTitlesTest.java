package rn.puzzle.interview.australia;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class MovieTitlesTest {

    @Test
    public void test1() {
        String[] res = getMovieTitles("spiderman");
        Assert.assertNotNull(res);
    }

    public String[] getMovieTitles(String substr) {
        String urlString = String.format("https://jsonmock.hackerrank.com/api/movies/search/?Title=%s", substr);
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection conn = null;
        try {
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream is = conn.getInputStream();


            String result = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));

            try {
                JSONObject jo = new JSONObject(result);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[0];
    }
}
