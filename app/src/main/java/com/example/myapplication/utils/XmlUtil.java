package com.example.myapplication.utils;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class XmlUtil {

    private static final String TAG = "XmlUtil";

    // 创建 XML 文件
    public static void createXml(Context context, String filename) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            XmlSerializer serializer = Xml.newSerializer();
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "root");
            serializer.endTag("", "root");
            serializer.endDocument();
            writer.close();
            Log.d(TAG, "XML file created: " + filename);
        } catch (IOException e) {
            Log.e(TAG, "Error creating XML file: " + e.getMessage());
        }
    }

    public static void addNode(Context context, String filename, String nodeName, String nodeValue) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader reader = new InputStreamReader(fis);
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, charsRead);
            }
            fis.close();

            String existingXml = stringBuilder.toString();

            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            if (!existingXml.contains("<root>")) {
                writer.write("<root>");
            }

            writer.write("<" + nodeName + ">" + nodeValue + "</" + nodeName + ">");

            if (!existingXml.contains("</root>")) {
                writer.write("</root>");
            }

            writer.close();
            Log.d(TAG, "Node added to XML file: " + nodeName + "=" + nodeValue);
        } catch (IOException e) {
            Log.e(TAG, "Error adding node to XML file: " + e.getMessage());
        }
    }


    // 删除节点从 XML 文件
    public static void deleteNode(Context context, String filename, String nodeName) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader reader = new InputStreamReader(fis);
            XmlSerializer serializer = Xml.newSerializer();
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            char[] buffer = new char[fis.available()];
            reader.read(buffer);
            String existingXml = new String(buffer);
            writer.append(existingXml.replace("<" + nodeName + "></" + nodeName + ">", ""));
            writer.close();
            reader.close();
            Log.d(TAG, "Node deleted from XML file: " + nodeName);
        } catch (IOException e) {
            Log.e(TAG, "Error deleting node from XML file: " + e.getMessage());
        }
    }

    // 修改节点值
    public static void updateNode(Context context, String filename, String nodeName, String newNodeValue) {
        deleteNode(context, filename, nodeName);
        addNode(context, filename, nodeName, newNodeValue);
        Log.d(TAG, "Node updated in XML file: " + nodeName + "=" + newNodeValue);
    }

    // 查询节点值
    public static String queryNode(Context context, String filename, String nodeName) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader reader = new InputStreamReader(fis);
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(reader);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                if (name != null && name.equals(nodeName)) {
                    return parser.nextText();
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            Log.e(TAG, "Error querying node from XML file: " + e.getMessage());
        }
        return null;
    }
}
