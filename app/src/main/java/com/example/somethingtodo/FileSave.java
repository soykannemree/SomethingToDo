package com.example.somethingtodo;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileSave {
    public static final String FILE = "listinf.dat";
    public static void writeListItem(ArrayList<String> item, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILE,Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(item);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String>  readListData(Context context){
        ArrayList<String> ItemList = null;
        try {
            FileInputStream fis =context.openFileInput(FILE);
            ObjectInputStream ois =new ObjectInputStream(fis);
            ItemList = (ArrayList<String>) ois.readObject();

        } catch (FileNotFoundException e) {

            ItemList=new ArrayList<>();

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return ItemList;

    }
}
