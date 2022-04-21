package core;

import json.UserHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Core {


  private JSONArray data;

  private UserHandler userHandler = new UserHandler();

  public Core() throws IOException {
  }

  public String logginn(String user, String pas) {
    JSONObject jsonUser = Validator.userexsist(user, data);
    if (jsonUser != null) {
      String pasHash = jsonUser.get("PassHash").toString();
      if (Encrypt.hash(pas).equals(pasHash)) {
        System.out.println("LOGG INN SUCSESS!");
        String dataStr = jsonUser.get("Data").toString();
        return dataStr;
      }
    }
    return null;
  }


  public JSONArray getdata() {
    return data;
  }

}
