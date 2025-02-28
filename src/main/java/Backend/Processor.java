package Backend;

import data.DataNameP008;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;
import java.util.stream.Collectors;


public class Processor  {

    private String sep;

    private String tabs = "|";


    private StringBuilder builder = new StringBuilder();


    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public String getTabs() {
        return tabs;
    }

    public void setTabs(String tabs) {
        this.tabs = tabs;
    }

    public void retreiveAllData(String parent, String dataJSON, String tabs){
        boolean isSout = false;

        JSONObject data = new JSONObject(dataJSON);

        if (parent != null && parent.contains("#")){

            List<String> splitedNested = Arrays.stream(parent.split("#")).collect(Collectors.toList());
            List<String> copyan = new ArrayList<>(splitedNested);

            if (splitedNested.size() == 1){
                retreiveAllData(splitedNested.get(0), data.toString(), tabs);
            }

            for (String nData : splitedNested){
//                System.out.println(nData);
//                System.out.println(dataJSON.substring(0,1));


                try {
                    // For json Object
                    data = data.getJSONObject(nData);
                    copyan.remove(0);

                    String newParent = String.join("#", copyan);
//                System.out.println(newParent);


                    if (!newParent.isEmpty()) {
                        retreiveAllData(newParent, data.toString(), tabs);
                    }
                    else {
                        return;
                    }
                }
                catch (JSONException ex){
//                    ex.printStackTrace();
                    JSONArray jsonArray = data.getJSONArray(nData);


                    copyan.remove(0);

                    String newParent = String.join("#", copyan);

                    // perlu check berapa banyak panjang length
                    if (!jsonArray.isEmpty()){
                        for (int i = 0 ; i < jsonArray.length() ; i++){
                            retreiveAllData(newParent, jsonArray.get(i).toString(),tabs);
                        }
                        return;
                    }
                    else {
                        return;
                    }
                }
                return;
//                System.out.print("");
            }
            return;

        }
        else {
            data = new JSONObject(dataJSON);
        }



        if (parent != null && !parent.isEmpty()){

            try {

                data = data.getJSONObject(parent);

                // display data
                this.builder.append(getJsonValue(parent));
                this.builder.append("\r\n");
//                System.out.println();
            }
            catch (JSONException ex){
                this.builder.append(getJsonValue(parent));
                this.builder.append("\r\n");
//                System.out.println(getJsonValue(parent));
                JSONArray jsonArray = data.getJSONArray(parent);

                // perlu check berapa banyak panjang length
                if (!jsonArray.isEmpty()){
                    for (int i = 0 ; i < jsonArray.length() ; i++){
                        retreiveAllData(null, jsonArray.get(i).toString(),tabs);
                    }
                    return;
                }
                else {
                    return;
                }
            }
        }
        tabs = tabs+this.getSep();

        Iterator<String> nData = data.keys();
        while (nData.hasNext()){

            String key = nData.next();
            String type = data.get(key).getClass().getSimpleName();
            String jsonString = data.get(key).toString();

//            System.out.println(key+" : "+type+seperatpr+seperatorValue);
//            System.out.println(type);
//            System.out.println(tabs+key);
//            System.out.println(jsonString);
            if (type.equalsIgnoreCase("JSONObject")){
                this.builder.append(tabs+getJsonValue(key));
                this.builder.append("\r\n");
//                System.out.println(tabs+getJsonValue(key));
                retreiveAllData(null, jsonString,tabs);
            }

            if (type.equalsIgnoreCase("JSONArray")){
                this.builder.append(tabs+getJsonValue(key));
                this.builder.append("\r\n");
//                System.out.println(tabs+getJsonValue(key));
                JSONArray jsonArray = new JSONArray(data.get(key).toString());

                // perlu check berapa banyak panjang length
                if (!jsonArray.isEmpty()){
                    for (int i = 0 ; i < jsonArray.length() ; i++){
                        retreiveAllData(null, jsonArray.get(i).toString(),tabs);
                    }
                }

            }

            if (type.equalsIgnoreCase("String")){
                isSout = true;
                this.builder.append(tabs+getJsonValue(key)+" : ");
                this.builder.append(data.get(key));
//                System.out.print(tabs+getJsonValue(key)+" : ");
//                System.out.print(data.get(key));
            }

            if (type.equalsIgnoreCase("Integer")){
                isSout = true;
                this.builder.append(tabs+getJsonValue(key)+" : ");
                this.builder.append(data.get(key));
//                System.out.print(tabs+getJsonValue(key)+" : ");
//                System.out.print(data.get(key));
            }

            if (isSout){
                this.builder.append("\r\n");
//                System.out.println();
            }
            System.out.print("");
            isSout = false;
        }



//        return stringBuilder;
    }

    public static String getJsonValue (String key){
        try {
            return DataNameP008.OBJECT_JSON.get(key.toLowerCase()).toString();
        }
        catch (JSONException ex){
            return key;
        }
    }

    public StringBuilder getBuilder() {
        return builder;
    }
}
