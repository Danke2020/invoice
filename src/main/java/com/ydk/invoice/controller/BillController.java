package com.ydk.invoice.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.spring.SpringPipelineFactory;
import com.ydk.invoice.htmlbean.CompanyPanel;
import com.ydk.invoice.htmlbean.CompanyPanelPipeline;
import com.ydk.invoice.model.BillInfo;
import com.ydk.invoice.repository.BillRepository;
import com.ydk.invoice.util.NumberToCN;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log
@CrossOrigin
@RestController
public class BillController {
    private final BillRepository billRepository;
    private final SpringPipelineFactory springPipelineFactory;

    @Autowired
    public BillController(BillRepository billRepository, SpringPipelineFactory springPipelineFactory) {
        this.billRepository = billRepository;
        this.springPipelineFactory = springPipelineFactory;
    }

    @SneakyThrows(IOException.class)
    @GetMapping(value = "/bill/{id}/download.xls", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getData(@PathVariable("id") int id) {
        val bill = billRepository.findById(id).orElseThrow(NullPointerException::new);
        return download(bill);
    }

    @GetMapping(value = "/company/key/{query}")
    public List<CompanyPanel> fetchList(@PathVariable String query) {
        CompanyPanelPipeline.companyPanels.clear();
        HttpGetRequest start = new HttpGetRequest("https://www.qichacha.com/tax_search?key=" + query);
        GeccoEngine.create()
                .pipelineFactory(springPipelineFactory)
                .classpath("com.ydk.invoice.htmlbean")
                //开始抓取的页面地址
                .start(start)
                //开启几个爬虫线程
                .thread(3)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .run();
        return CompanyPanelPipeline.companyPanels;
    }

    @SuppressWarnings("unchecked")
    private byte[] download(BillInfo billInfo) throws IOException {
        val exportParams = new TemplateExportParams("templates/temp.xls");
        val objectMapper = new ObjectMapper();
        val convertValue = objectMapper.convertValue(billInfo, Map.class);
        convertValue.put("totalCN", NumberToCN.number2CNMontrayUnit(billInfo.getTotal()));
        @Cleanup val workbook = ExcelExportUtil.exportExcel(exportParams, convertValue);
        @Cleanup val outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream.toByteArray();
    }

}
