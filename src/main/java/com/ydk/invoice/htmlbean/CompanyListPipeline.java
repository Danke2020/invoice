package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.scheduler.Scheduler;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.scheduler.StartScheduler;
import com.geccocrawler.gecco.spider.HrefBean;

import java.util.ArrayList;
import java.util.List;

@PipelineName(value="companyListPipelines")
public class CompanyListPipeline implements Pipeline<CompanyList> {
    public static List<HttpRequest> sortRequests = new ArrayList<>();
    @Override
    public void process(CompanyList companyList) {
        HttpRequest request = companyList.getRequest();
        for (HrefBean hrefBean : companyList.getDetails()) {
            String url = hrefBean.getUrl();
            System.out.println("url = " + url);
//            sortRequests.add(request.subRequest(url));
            DeriveSchedulerContext.into(request.subRequest(url));
        }
    }
}
