package com.emailBackEnd.emailBackEnd.Controller;

import com.emailBackEnd.emailBackEnd.Filter.FilterFacade;
import com.emailBackEnd.emailBackEnd.Message;
import com.emailBackEnd.emailBackEnd.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")

public class FilterSortSearch {
    @PostMapping("/Filter")
    //Filter type means if he wants to filer by subject or reciever or both
    public List<Message> Filter(@RequestBody String UserName_FolderName_FilterType_Subj_Reciever ){
        return FilterFacade.filter(UserName_FolderName_FilterType_Subj_Reciever);
    }
    @PostMapping("/Search")
    public List<Message> Search(@RequestBody String ActiveUserName_FolderName_Search){
        return Service.Search(ActiveUserName_FolderName_Search);
    }

}
