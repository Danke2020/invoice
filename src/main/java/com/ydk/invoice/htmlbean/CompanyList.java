package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

@Data
@Gecco(matchUrl = "https://www.qichacha.com/tax_search?key={query}", pipelines = {"companyListPipelines"})
public class CompanyList implements HtmlBean {

    @Request
    private HttpRequest request;

    @HtmlField(cssPath="section.panel a")
    private List<HrefBean> details;

    public static void main(String[] args) {
        //先获取分类列表
        HttpGetRequest start = new HttpGetRequest("https://www.qichacha.com/tax_search?key=福建火");

        GeccoEngine.create().debug(false)
                .classpath("com.ydk.invoice.htmlbean")
                //开始抓取的页面地址
                .start(start)
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .run();
    }
}
