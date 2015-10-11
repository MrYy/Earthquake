package com.example.ge.earthquake;

import android.app.ListFragment;
import android.os.Bundle;

import android.util.Log;
import android.widget.ArrayAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by ge on 2015/10/11.
 */
public class EarthquakeListFragment extends ListFragment {
    private static final String T = "EARTHQUAKE";
    ArrayList<Quake> arrayList = new ArrayList<Quake>();
    ArrayAdapter<Quake> arrayAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int layoutID = android.R.layout.simple_list_item_1;
        arrayAdapter = new ArrayAdapter<Quake>(getActivity(), layoutID, arrayList);
        setListAdapter(arrayAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                refreshEarthquakes();
            }
        }).start();
    }

    public void refreshEarthquakes() {
        URL url;

        String quakeFeed = getString(R.string.quake_feed);
        try {
            url = new URL(quakeFeed);
            URLConnection connection;
            connection = url.openConnection();

            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            int responseCode = httpURLConnection.getResponseCode();
            boolean b = responseCode == HttpURLConnection.HTTP_OK;
            Log.d(T, Boolean.toString(b));
            if (b) {
                InputStream inputStream = httpURLConnection.getInputStream();

                //ʵ�����������ڹ����л�ȡ�µ�bulder
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                //��������Դ��builder��Դƥ��������ó�document
                Document document = documentBuilder.parse(inputStream);
                Element docEle=document.getDocumentElement();
                //����ɵ�����
                arrayList.clear();



            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }
}
