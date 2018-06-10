package com.hnsle.webaaa.controller;

import com.hnsle.webaaa.Parser;
import com.hnsle.webaaa.model.DetailedModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailedController {
    Parser parser = new Parser();
    DetailedModel dm = new DetailedModel();

    @RequestMapping(method= RequestMethod.GET, path="/detailedService")
    public DetailedModel responseInform(@RequestParam("id") int id) throws Exception {
        return parser.parsedDetailedService(id);
    }

}
