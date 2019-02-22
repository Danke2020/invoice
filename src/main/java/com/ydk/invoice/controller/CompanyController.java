package com.ydk.invoice.controller;

import com.ydk.invoice.model.Bill;
import com.ydk.invoice.model.Company;
import com.ydk.invoice.repository.CompanyRepository;
import com.ydk.invoice.util.WordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    private Map<String, Object> root = new HashMap<>();

    @GetMapping(path = "/company")
    public @ResponseBody
    List<Company> findAll(){
        return companyRepository.findAll();
    }

    @GetMapping(path = "/temp")
    public ResponseEntity<FileSystemResource> putData() {
        Company pc = companyRepository.getOne("371302797304481");
        Company bc = companyRepository.getOne("371325075785711");
        Bill b = new Bill();
        b.setPc(pc);
        b.setBc(bc);
        b.setSigndate(new Date());
        root.put("b", b);
        return export(root);
    }

    private ResponseEntity<FileSystemResource> export(Map<String, Object> map) {
        File file = null;
        file = WordGenerator.createDoc(map,"temp3.ftl");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".doc");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

}
