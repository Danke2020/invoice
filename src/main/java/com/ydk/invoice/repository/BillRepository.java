package com.ydk.invoice.repository;

import com.ydk.invoice.model.BillInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "bill", collectionResourceRel = "bill")
public interface BillRepository extends JpaRepository<BillInfo, Integer> {

}
