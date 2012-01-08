
package com.myor.c2dm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.myor.c2dm.utils.HTTPUtils;

public class AuthenticationUtils {

    private static Log log = LogFactory.getLog (AuthenticationUtils.class);

    public static String getAuthCode (String email, String psswd) {

        String AUTH_CODE = null;

        HttpClient client = new DefaultHttpClient ();
        HttpPost post = new HttpPost ("https://www.google.com/accounts/ClientLogin");

        try {

            List <NameValuePair> nameValuePairs = new ArrayList <NameValuePair> (1);
            nameValuePairs.add (new BasicNameValuePair ("Email", email));
            nameValuePairs.add (new BasicNameValuePair ("Passwd", psswd));
            nameValuePairs.add (new BasicNameValuePair ("accountType", "GOOGLE"));
            nameValuePairs.add (new BasicNameValuePair ("source", "Google-cURL-Example"));
            nameValuePairs.add (new BasicNameValuePair ("service", "ac2dm"));

            post.setEntity (new UrlEncodedFormEntity (nameValuePairs));
            HttpResponse response = client.execute (post);
            BufferedReader rd = new BufferedReader (new InputStreamReader (response.getEntity ().getContent ()));

            String line = "";
            while ( (line = rd.readLine ()) != null) {
                if (line.startsWith ("Auth=")) {
                    AUTH_CODE = line.substring (5);
                }

            }
        } catch (IOException e) {
            e.printStackTrace ();
        }

        log.debug ("In setAuthCode AUTH_CODE generated is " + AUTH_CODE);
        return AUTH_CODE;

    }

    public static void main (String[] args) {

        // config your own account and password here
        String senderAccountEmail = "zhgq.developer@gmail.com";
        String senderAccountPasswd = "zhgq1989Hacker";

        System.out.println (AuthenticationUtils.getAuthCode (senderAccountEmail, senderAccountPasswd));
    }
}
