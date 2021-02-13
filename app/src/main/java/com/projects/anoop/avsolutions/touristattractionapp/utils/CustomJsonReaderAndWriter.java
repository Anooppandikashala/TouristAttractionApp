package com.projects.anoop.avsolutions.touristattractionapp.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class CustomJsonReaderAndWriter {

    private  static  final  String FILE_NAME = "file.json";
    static File file = null;

    private static  String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("tourist_attraction_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static  boolean createFile(Context context)
    {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        file = new File(context.getFilesDir(),FILE_NAME);
        if(file == null)
        {
            return  false;
        }
        if(!file.exists())
        {
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                String init = loadJSONFromAsset(context);
                bufferedWriter.write(init);
                bufferedWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return  false;
            }

        }
        return true;

    }

    public boolean writeRatingToFile()
    {
        return  false;
    }


    public  static JSONObject jsonReader(Context context)
    {
        if(!createFile(context))
        {
            return  null;
        }

        StringBuffer output = new StringBuffer();
        String response = null;
        try {
            FileReader fileReader = new FileReader(file.getAbsolutePath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
            {
                output.append(line+"\n");
            }
            response = output.toString();
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return null;
    }



}
