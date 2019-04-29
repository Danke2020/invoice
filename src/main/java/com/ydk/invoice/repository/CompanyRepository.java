package com.ydk.invoice.repository;

import com.ydk.invoice.model.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "company", collectionResourceRel = "company")
public interface CompanyRepository extends JpaRepository<CompanyInfo, String> {

}
