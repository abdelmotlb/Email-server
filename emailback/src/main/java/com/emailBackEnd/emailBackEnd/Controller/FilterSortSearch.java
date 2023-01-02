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
    //Filter type means if he wants to filer by subject or sender or both
    @PostMapping("/Filter")
    public List<Message> Filter(@RequestBody String UserName_FolderName_FilterType_Subj_Sender ){
        return FilterFacade.filter(UserName_FolderName_FilterType_Subj_Sender);
    }
    @PostMapping("/Search")
    public List<Message> Search(@RequestBody String ActiveUserName_FolderName_Search){
        return Service.Search(ActiveUserName_FolderName_Search);
    }
    // SortBy has the arguments of Date, Sender, Reciever, Importance, Subject
    @PostMapping("/Sort")
    public List<Message> Sort(@RequestBody String ActiveUserName_FolderName_SortBy_IsAscending){
        return Service.Sort(ActiveUserName_FolderName_SortBy_IsAscending);
    }
}
