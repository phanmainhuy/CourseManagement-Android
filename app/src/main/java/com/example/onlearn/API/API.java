package com.example.onlearn.API;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class API {
    Context context;
    RequestQueue requestQueue;

    public API(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }



   public void CallAPI(String urlAPI, int method, String requestBody, Map<String, String> params, Map<String, String> paramsHeaders, ICallBack iCallBack) {
//        if(networkUtil.getConnectivityStatusString(context) == 0) return;
        StringRequest stringRequest = new StringRequest(method, urlAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //call back data response
                iCallBack.ReponseSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.ReponseError(error.getMessage() + "");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // post using form data
                if (params == null) {
                    return super.getParams();
                }
                return params;

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return paramsHeaders == null ? super.getHeaders() : paramsHeaders;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                // post using json body
                try {
                    return requestBody == null ? super.getBody() : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }


        };
        requestQueue.add(stringRequest);
    }
}
