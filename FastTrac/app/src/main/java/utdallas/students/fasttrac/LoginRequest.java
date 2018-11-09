package utdallas.students.fasttrac;

import com.android.volley.Request;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = # INSERT URL LINK HERE#
    private Map<String, String> params;

    public RegisterRequest(String username, String password, Response.Lstener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, Listener, null)
        params = new HsahMap<>();
        params.put("username", username);
        params.put("password", password);


    }

    public Map<String, String> getParams(){
        return params;
    }
}
