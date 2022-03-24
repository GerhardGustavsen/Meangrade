package core;

import json.CourseFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Course {
    private String code;
    private String name;
    private String description;
    private int[] results;

    public Course(String c, String n, String d, int[] r) {
        code = c;
        name = n;
        description = d;
        results = r;
    }

    public String getCode() {
        return code;
    };

    public String getName() {
        return name;
    };

    public String getDesc() {
        return description;
    };

    public int[] getRes() {
        return results;
    };
}