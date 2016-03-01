package com.jhonyu.framework.util.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
    
    /**
     * description: Map转换json(数组)
     * @author: tangjiabin
     * @date: Apr 25, 2015 1:06:36 AM
     * @param map
     * @return
     */
    public static String mapToJsonArray(Map<String,Object> map){
        JSONArray json = JSONArray.fromObject(map); 
        return json.toString();
    }
    
    /**
     * description: Map转换json(对象)
     * @author: tangjiabin
     * @date: Apr 25, 2015 1:06:36 AM
     * @param map
     * @return
     */
    public static String mapToJsonObj(Map<String,Object> map){
        JSONObject json = JSONObject.fromObject(map); 
        return json.toString();
    }

    /**
     * description: 任意对象转换成json
     * 
     * @author: tangjiabin
     * @date: Apr 23, 2015 4:07:31 PM
     * @param object
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static String objectToJson(Object object) {
        StringBuilder json = new StringBuilder();
        if (object == null) {
            json.append("\"\"");
        } else if (object instanceof String) {
            json.append("\"").append((String) object).append("\"");
        } else if (object instanceof Integer) {
            json.append("\"").append(String.valueOf(object)).append("\"");
        } else if (object instanceof Date) {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            json.append("\"").append(formatter.format((Date) object)).append(
                    "\"");
        } else if (object instanceof List<?>) {
            json.append("\"").append(listToJson((List<?>) object)).append("\"");
        } else if(object instanceof Map){
            json.append(mapToJsonObj((Map)object));
        }else{
            json.append(beanToJson(object));
        }
        return json.toString();
    }

    /**
     * description: bean转成对象
     * @author: tangjiabin
     * @date: Apr 23, 2015 4:11:19 PM
     * @param bean
     * @return
     */
    public static String beanToJson(Object bean){
        JSONObject  jsonObject= JSONObject.fromObject(bean);
        return jsonObject.toString();
    }

    /**
     * description: list 转成对象
     * @author: tangjiabin
     * @date: Apr 23, 2015 4:11:41 PM
     * @param list
     * @return
     */
    public static String listToJson(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(objectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    
    /**
     * description: Json转list
     * @author: tangjiabin
     * @date: Apr 23, 2015 4:33:26 PM
     * @param jsonString
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        JSONArray array = JSONArray.fromObject(jsonString);
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            T t = (T)JSONObject.toBean(jsonObject, clazz);
            list.add(t);
        }
        return list;
    }
    
    /**
     * description: Json转bean
     * @author: tangjiabin
     * @date: Apr 23, 2015 4:37:09 PM
     * @param jsonString
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public  static <T> T jsonToBean(String jsonString, Class<T> clazz) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return (T) JSONObject.toBean(jsonObject, clazz);
    }
    
    
    /**
     * @Description: JSON转成Map
     * @userName: tangjb
     * @date: Jul 14, 2015 2:19:20 PM
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> jsonToMap(String jsonStr){  
        HashMap<String, Object> map = new HashMap<String, Object>();  
        //最外层解析  
        JSONObject json = JSONObject.fromObject(jsonStr);  
        for(Object k : json.keySet()){  
            Object v = json.get(k);   
            //如果内层还是数组的话，继续解析  
            if(v instanceof JSONArray){  
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
                Iterator<JSONObject> it = ((JSONArray)v).iterator();  
                while(it.hasNext()){  
                    JSONObject json2 = it.next();  
                    list.add(jsonToMap(json2.toString()));  
                }  
                map.put(k.toString(), list);  
            } else {  
                map.put(k.toString(), v);  
            }  
        }  
        return map;  
    }  
}
