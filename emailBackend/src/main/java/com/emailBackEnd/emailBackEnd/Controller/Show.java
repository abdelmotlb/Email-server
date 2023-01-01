package com.emailBackEnd.emailBackEnd.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailBackEnd.emailBackEnd.Message;
import com.emailBackEnd.emailBackEnd.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Show {
    @PostMapping("/getMessages")
    public List<Message> getMessages(@RequestBody String activeAndfile) {

        System.out.println("in get messages");

        String[] arr = activeAndfile.split("\\$");
        String active = arr[0];
        String file = arr[1];
        List<Message> inboxMessages = Service.getJasonMessageList(active, file);
        return inboxMessages;
    }

    // downloading attachments parts:
    @PostMapping("/downloadAttach")
    public String download(@RequestBody String fileName) {

        System.out.println("file name in download attach " + fileName);

        String[] filesAndPath = fileName.split("\\#");
        for (int i = 0; i < filesAndPath.length - 1; i++) {
            File src = new File("./AllUsersFolders/" + "attaches/" + filesAndPath[i]);
            String name = filesAndPath[i].split("\\$")[1];
            File dest = new File(filesAndPath[filesAndPath.length - 1] + "/" + name);
            try {
                Files.copy(src.toPath(), dest.toPath());
                System.out.println("downed");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("cannot down");
            }

        }
        return "downloaded";
    }

    // delete messages from any place to trash or delete forever from trash
    @PostMapping("/deleteSelected")
    public List<Message> deleteMessages(@RequestBody String idsActiveSource) {
        System.out.println("in delete");
        // public List<Message> deleteMessages(String idsActiveSource) {
        String[] filesAndPath = idsActiveSource.split("\\$");
        String active = filesAndPath[0];
        String source = filesAndPath[1];
        int n = filesAndPath.length;
        List<Message> messageFiles = Service.getJasonMessageList(active, source);
        List<Message> trash = null;
        if (!source.equals("trash"))
            trash = Service.getJasonMessageList(active, "trash");

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < messageFiles.size(); j++) {
                if (messageFiles.get(j).getId() == Long.parseLong(filesAndPath[i])) {
                    if (!source.equals("trash")) {
                        trash.add(messageFiles.get(j));
                        messageFiles.remove(j);
                    } else
                        messageFiles.remove(j);
                }
            }
        }
        ObjectMapper OM = new ObjectMapper();
        FileOutputStream tr, fos;
        try {
            tr = new FileOutputStream("allUsersFolders/" + active + "/" + active + "_"
                    + "trash.json");
            fos = new FileOutputStream(
                    "allUsersFolders/" + active + "/" + active + "_"
                            + source + ".json");
            OM.writeValue(fos, messageFiles);
            if (!source.equals("trash")) {
                OM.writeValue(tr, trash);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messageFiles;
    }

    @PostMapping("/MovetoFolder")
    public void moveToFolder(@RequestBody String filesActiveSourceDest) {
        // public void moveToFolder(@RequestBody String filesActiveSourceDest) {
        String[] files = filesActiveSourceDest.split("\\$");
        int n = files.length;
        String active = files[0];
        String source = files[1];
        String destination = files[2];
        List<Message> messageFiles = Service.getJasonMessageList(active, source);
        List<Message> newFolderMessages = Service.getJasonMessageList(active, destination);
        for (int i = 3; i < n; i++) {
            for (int j = 0; j < messageFiles.size(); j++) {
                if (messageFiles.get(j).getId() == Long.parseLong(files[i])) {
                    newFolderMessages.add(messageFiles.get(j));
                    messageFiles.remove(j);
                }
            }
        }
        ObjectMapper OM = new ObjectMapper();
        FileOutputStream dest, src;
        try {

            dest = new FileOutputStream("allUsersFolders/" + active + "/" + active + "_" + destination
                    + ".json");
            src = new FileOutputStream(
                    "allUsersFolders/" + active + "/" + active + "_"
                            + source + ".json");
            OM.writeValue(src, messageFiles);
            OM.writeValue(dest, newFolderMessages);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @PostMapping("/CreatFolder")
    public void creatFolder(String userAndFile) {
        String[] twoParam = userAndFile.split("\\$");
        String active = twoParam[0];
        String name = twoParam[1];
        String[] arr = {};
        ObjectMapper map = new ObjectMapper();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("./AllUsersFolders/" + active + "/" + active + "_" + name + ".json");
            map.writeValue(fos, arr);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
