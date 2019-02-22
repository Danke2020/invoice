package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Gecco(matchUrl = "https://github.com/{user}/{project}", pipelines = "consolePipeline")
public class MyGithub implements HtmlBean {

    @RequestParameter("user")
    private String user;

    @RequestParameter("project")
    private String project;

    @Text
    @HtmlField(cssPath = ".repository-meta-content")
    private String title;

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(2) .social-count")
    private int star;

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(3) .social-count")
    private int fork;

    public static void main(String[] args) {
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("com.ydk.invoice.htmlbean")
                //开始抓取的页面地址
                .start("https://github.com/xtuhcy/gecco")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .start();
    }
}