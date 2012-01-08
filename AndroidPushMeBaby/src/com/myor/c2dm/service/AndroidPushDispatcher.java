
package com.myor.c2dm.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.myor.c2dm.utils.AuthenticationUtils;
import com.myor.c2dm.utils.Constants;
import com.myor.c2dm.utils.StringUtil;

public class AndroidPushDispatcher {


    public C2DMServerResponseMessage sendNotification (String authToken, String registrationId) {


        DefaultHttpClient client = new DefaultHttpClient ();
        HttpPost httppost = new HttpPost (Constants.SEND_URL);


        List <NameValuePair> formparams = new ArrayList <NameValuePair> ();
        formparams.add (new BasicNameValuePair (Constants.PARAM_REGISTRATION_ID, registrationId));
        formparams.add (new BasicNameValuePair ("data.msg", "JTBC C2DM Test 테스트"));
         //formparams.add (new BasicNameValuePair ("data.re", "VIDEO"));
//        formparams.add (new BasicNameValuePair ("data.re", "NB10016687"));

        formparams.add (new BasicNameValuePair (Constants.PARAM_COLLAPSE_KEY, "0"));


        System.out.println ("sending to : " + Constants.SEND_URL);
        System.out.println ("" + Constants.PARAM_REGISTRATION_ID + ":" + registrationId);
        System.out.println ("data.msg:" + "JTBC C2DM Test 테스트");
        System.out.println ("data.re" + "NB10016687");
        System.out.println ("" + Constants.PARAM_COLLAPSE_KEY + ":" + "77");
        System.out.println ("infomation in header : key :" + Constants.AUTHORIZATION + " ,  value:GoogleLogin auth="
                + authToken);

        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity (formparams, Constants.UTF8);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace ();
        }
        httppost.setEntity (entity);
        Header header = new BasicHeader (Constants.AUTHORIZATION, "GoogleLogin auth=" + authToken);
        Header header2 = new BasicHeader ("Content-length=", "" + entity.getContentLength ());
        httppost.addHeader (header);
        httppost.addHeader (header2);
        httppost.getParams ().setParameter ("Content-length:", "" + entity.getContentLength ());
        HttpContext localContext = new BasicHttpContext ();
        HttpResponse response = null;
        try {
            response = client.execute (httppost, localContext);
            HttpEntity entity2 = response.getEntity ();

            String resposeBody = StringUtil.convertStreamToString (entity2.getContent ());

            C2DMServerResponseMessage c2dmResp = new C2DMServerResponseMessage ();
            c2dmResp.setStatusLine (response.getStatusLine ().toString ());
            c2dmResp.setContent (resposeBody);


            return c2dmResp;

        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }

    public static void main (String[] args) {

        // config your own account and password here
        String senderAccountEmail = "yourname@gmail.com";
        String senderAccountPasswd = "your-gmail-password";


        // String senderAccountEmail = "zhgq.developer@gmail.com";
        // String senderAccountPasswd = "zhgq1989Hacker";

        String authToken = AuthenticationUtils.getAuthCode (senderAccountEmail, senderAccountPasswd);

        String registratinId = "APA91bEubbrqQ77FAqFaBEOSYxOoA6-R4PKA2pwYU4caI-uBlXBntfB1NVZ4OhMapJpQfQvO_5vhkxL1C_wTy9L5mT5gYEfKPZwECoTVejMpVZ1OJGlZUKekGqciwUM8CHyJDkwN1ATt";

        // tznim
        // String registratinId =
        // "APA91bH8nS6sck3lCSaGslBrODpB7evmlKm5I_KhczW4iXosXGyn0PY9yfLLlRWbjlXNIu0MhJlqvKo_1sCX4kqYdKs9sRkBqG5t-qWF8fuUzO2qTnT4Oby5XqtWIHgER1L_4Zqp927x";

        // bmkim
        // String registratinId =
        // "APA91bH1324SXfbAfXVnYoW23OkDN_lB9P-NFYJaG-5R2B8DXgQin3FNWi05S7-JsQ3AjnVgfXdEouHGHHCumv-NZ1EQfuYCmVQlyuvsR8Xbjz_aDDSuMpDzpgVz1VblOO7bImSakrce";

        System.out.println ("auth token from google : \n" + authToken);
        System.out.println ("-----------------------------------------------");
        System.out.println ("registrstion id from user : \n" + registratinId);
        System.out.println ("-----------------------------------------------");
        System.out.println ("sending notificaiton..");

        AndroidPushDispatcher push = new AndroidPushDispatcher ();
        System.out.println (push.sendNotification (authToken, registratinId));
    }
}
