/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.GoogleAccount;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

public class GoogleLogin {

    public static String getToken(String code) throws ClientProtocolException, IOException {

        String response = Request.Post(Constant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Constant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Constant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Constant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Constant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;

    }

    public static GoogleAccount getUserInfo(final String accessToken) throws IOException {
        String link = Constant.GOOGLE_LINK_GET_USER_INFO + accessToken;

        String response = Request.Get(link).execute().returnContent().asString();

        GoogleAccount googlePojo = new Gson().fromJson(response, GoogleAccount.class);

        return googlePojo;
    }
}
