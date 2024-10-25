/*
 * Copyright 2024 OmniOne.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.omnione.did.cli.command.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.omnione.did.cli.command.Omni;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CommandUtils {

    // file read to string
    public static String readFileToString(File file) {
        BufferedReader br = null;
        InputStreamReader ir = null;
        FileInputStream fr = null;
        String data = null;
        try {
            fr = new FileInputStream(file);
            ir = new InputStreamReader(fr, StandardCharsets.UTF_8);
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
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                ir.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error occurred while working with the file. Verify that the file exists and check access permissions.");
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
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error occurred while working with the file. Verify that the file exists and check access permissions.");
            }
        }
    }

    // print command
    public static void printedCommandMessage(Object child) {
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