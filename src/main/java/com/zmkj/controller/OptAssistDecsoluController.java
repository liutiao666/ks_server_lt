package com.zmkj.controller;

import com.zmkj.entity.mysql.OptAssistDecsolu;
import com.zmkj.service.OptAssistDecsoluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/optAssist")
public class OptAssistDecsoluController {

    @Autowired
    OptAssistDecsoluService optAssistDecsoluService;

    @GetMapping("/search")
    public List<OptAssistDecsolu> searchByStatus(@RequestParam("status") String status) {
        return optAssistDecsoluService.searchDecsolus(status);
    }
}
