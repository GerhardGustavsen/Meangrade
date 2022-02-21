package core;

import json.PersistantFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class User {

  private JSONArray data = new JSONArray();

  public User() {
    data = PersistantFile.load();
  }

  public void newprofile(String user, String pas) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("UserName", user);
    map.put("PassHash", Encrypt.hash(pas));
    map.put("Data", "");

    //Data is stored with course code like this: TDT1000#A, TDT1001#B, 

    data.add(map);
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

  public boolean delete(String name) {
    ArrayList<JSONObject> newdata = new ArrayList<JSONObject>();
    boolean awnser = false;

    if (data != null) {
      Iterator<?> iterator = data.iterator();
      while (iterator.hasNext()) {
        JSONObject userobj = (JSONObject) iterator.next();
        String listname = userobj.get("UserName").toString();
        if (!name.equals(listname)) {
          newdata.add(userobj);
        }
      }
      if (data != newdata) {
        data = (JSONArray) newdata;
        PersistantFile.save(data);
        awnser = true;
      }
    }
    return awnser;
  }

  public boolean addData(String user, String dat) {
    JSONObject jsonUser = Validator.userexsist(user, data);

    if (jsonUser != null) {
      delete(user);

      jsonUser.put("Data", dat);

      data.add(jsonUser);

      PersistantFile.save(data);
      return true;
    }
    return false;
  }

  public JSONArray getdata() {
    return data;
  }

}