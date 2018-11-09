package utdallas.students.fasttrac;

import com.android.volley.toolbox.StringRequest;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = # INSERT URL LINK HERE#
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String password, Response.Lstener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, Listener, null)
        params = new HsahMap<>();
        params.put("name",name);
        params.put("username", username);
        params.put("password", password);



    }

    public Map<String, String> getParams(){
        return params;
    }


}
