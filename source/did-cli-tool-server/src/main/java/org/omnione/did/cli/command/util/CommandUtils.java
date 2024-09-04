/* 
 * Copyright 2024 Raonsecure
 */

package org.omnione.did.cli.command.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.omnione.did.cli.command.Omni;

import java.io.*;

public class CommandUtils {

    // file read to string
    public static String readFileToString(File file) {
        BufferedReader br = null;
        InputStreamReader ir = null;
        FileInputStream fr = null;
        String data = null;
        try {
            fr = new FileInputStream(file);
            ir = new InputStreamReader(fr, "utf-8");
            br = new BufferedReader(ir);
            StringBuffer buf = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                buf.append(line);
            }

            data = buf.toString();

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                br.close();
                ir.close();
                fr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return data;
    }

    public static String toPrettyFormat(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        JsonObject json = gson.fromJson(jsonString, JsonObject.class);
        String prettyJson = gson.toJson(json);
        return prettyJson;
    }

    // string to file
    public static void writeFile(File file, String data) {
        try {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            output.write(data);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // print commamnd
    public static void printdCommadMssage(Object child) {
        String packageName = child.getClass().getName();
        String[] array = packageName.split("\\.");

        System.out.print("Command:	");
        System.out.print(array[array.length - 2]);
        System.out.print("->");
        System.out.println(array[array.length - 1]);

        if (Omni.DEBUG) {
            System.out.print("Debug Mode:	Input Values->");
            System.out.println(child.toString());
        }
    }

}