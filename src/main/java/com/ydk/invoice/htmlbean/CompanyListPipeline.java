package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;
import org.springframework.stereotype.Service;

@Service("companyListPipeline")
public class CompanyListPipeline implements Pipeline<CompanyList> {

    @Override
    public void process(CompanyList companyList) {
        HttpRequest request = companyList.getRequest();
        for (HrefBean hrefBean : companyList.getDetails().subList(0, 5)) {
            String url = hrefBean.getUrl();
            System.out.println("url = " + url);
            DeriveSchedulerContext.into(request.subRequest(url));
        }
    }
}
