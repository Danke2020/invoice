package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.List;

@Data
@Gecco(matchUrl = "https://www.qichacha.com/tax_search?key={query}", pipelines = {"companyListPipeline"})
public class CompanyList implements HtmlBean {

    @Request
    private HttpRequest request;

    @HtmlField(cssPath="section.panel a")
    private List<HrefBean> details;

}
