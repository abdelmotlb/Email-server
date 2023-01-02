package com.emailBackEnd.emailBackEnd;

import java.io.File;
import java.io.FileOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserFactory {

    public UserFactory() {
    }

    public boolean creatUser(String email, String pass, String fname, String lname, String gender) {
        if (Service.isValidName(email)) {
            addUser(User.getInstance(email, pass, fname, lname, gender));
            creatUserFiles(email);
            return true;
        } else
            return false;

    }

    // add a user to all usders json file
    public static void addUser(User newUser) {
        try {
            Service.all = Service.getJasonList("allUsers.json");
            Service.all.add(newUser);
            ObjectMapper OM = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream("allUsers.json");
            OM.writeValue(fos, Service.all);
        } catch (Exception e) {
            System.out.println("not found file");
        }
    }

    private void creatUserFiles(String filename) {
        try {
            File f1 = new File("./allUsersFolders/" + filename);
            f1.mkdir();
            String path = f1.getAbsolutePath();
            String[] arr = {};
            ObjectMapper map = new ObjectMapper();
            FileOutputStream fos = new FileOutputStream(path + "/" + filename + "_inbox.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_sent.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_trash.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_draft.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_cntacts.json");
            map.writeValue(fos, arr);
            fos = new FileOutputStream(path + "/" + filename + "_folders.json");
            map.writeValue(fos, arr);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
