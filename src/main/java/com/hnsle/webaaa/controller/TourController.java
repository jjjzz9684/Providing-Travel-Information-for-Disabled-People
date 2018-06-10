package com.hnsle.webaaa.controller;

import com.hnsle.webaaa.Parser;
import com.hnsle.webaaa.model.TourListModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class TourController {
    Parser parser = new Parser();

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET, path="/areaBasedSearch")
    public TourListModel searchByArea(@RequestParam("code1") int areaCode, @RequestParam("code2") int sigunguCode) throws Exception {
        return new TourListModel(parser.parseAreaBasedList(areaCode, sigunguCode));
    }//areaModel 을 return

}
