package com.ydk.invoice.controller;

import com.github.promeg.pinyinhelper.Pinyin;
import com.ydk.invoice.model.CompanyInfo;
import com.ydk.invoice.repository.CompanyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Log
@RepositoryRestController
public class CompanyController {

    private final CompanyRepository repository;

    @Autowired
    public CompanyController(CompanyRepository repo) {
        repository = repo;
    }

    @GetMapping(value = "/company/search/list")
    public @ResponseBody
    ResponseEntity<?> findByNameContainsPinYin(@RequestParam(value = "q") String q) {
        List<CompanyInfo> companyInfos = repository.findAll();
        companyInfos = companyInfos.stream().filter(isContainsPinYin(q)).collect(Collectors.toList());

        //
        // do some intermediate processing, logging, etc. with the producers
        //

        Resources<CompanyInfo> resources = new Resources<>(companyInfos);

        resources.add(linkTo(methodOn(CompanyController.class).findByNameContainsPinYin(q)).withSelfRel());

        // add other links as needed

        return ResponseEntity.ok(resources); 
    }

    public static Predicate<CompanyInfo> isContainsPinYin(String q) {
        return companyInfo -> {
            final String capsQuery = q.toUpperCase();
            final String separator = ",";
            String pinyinWithoutSpt = Pinyin.toPinyin(companyInfo.getName(),"");
            String pinyin = Pinyin.toPinyin(companyInfo.getName(), separator);
            final String[] pinyinArr = pinyin.split(separator);
            String pinyinHeader = Stream.of(pinyinArr).map(e -> e.substring(0,1)).reduce("", String::concat);
            return pinyinWithoutSpt.contains(capsQuery) || pinyinHeader.contains(capsQuery);
        };
    }
}