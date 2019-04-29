package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

@Data
@Gecco(matchUrl = "https://www.qichacha.com/tax_view?keyno={keyNo}", pipelines = {"companyPanelPipeline"})
public class CompanyPanel implements HtmlBean {

    @RequestParameter(value = "keyNo")
    private String keyNo;

    @Text
    @HtmlField(cssPath = "div.panel-body.m-t.m-b-lg > form > div:nth-child(1) > div > p")
    private String name;

    @Text
    @HtmlField(cssPath = "div.panel-body.m-t.m-b-lg > form > div:nth-child(2) > div > p")
    private String creditCode;

    @Text
    @HtmlField(cssPath = "div.panel-body.m-t.m-b-lg > form > div:nth-child(3) > div > p")
    private String address;

    @Text
    @HtmlField(cssPath = "div.panel-body.m-t.m-b-lg > form > div:nth-child(4) > div > p")
    private String tel;

    @Text
    @HtmlField(cssPath = "div.panel-body.m-t.m-b-lg > form > div:nth-child(5) > div > p")
    private String bank;

    @Text
    @HtmlField(cssPath = "div.panel-body.m-t.m-b-lg > form > div:nth-child(6) > div > p")
    private String bankAccount;

}
