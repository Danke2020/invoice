package com.ydk.invoice.htmlbean;

import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("companyPanelPipeline")
public class CompanyPanelPipeline implements Pipeline<CompanyPanel> {
    public static List<CompanyPanel> companyPanels = new ArrayList<>();

    @Override
    public void process(CompanyPanel companyPanel) {
        System.out.println("companyPanel = " + companyPanel);
        companyPanels.add(companyPanel);
    }
}
