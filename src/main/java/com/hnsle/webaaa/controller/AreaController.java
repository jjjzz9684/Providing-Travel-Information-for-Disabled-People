package com.hnsle.webaaa.controller;

import com.hnsle.webaaa.model.AreaModel;
import com.hnsle.webaaa.Parser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AreaController {
    List<String> result = new ArrayList<String>();
    Parser parser = new Parser();

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET, path="/area")
    public AreaModel searchById(@RequestParam(value="id") int areaCode) throws Exception {
           // System.out.println(parser.getAreaListURL(areaCode));
            return parser.getAreaListURL(areaCode);
    }//areaModel 을 return

}
