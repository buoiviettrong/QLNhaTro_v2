package com.Nixagh.Learn.KCP.customer.customerSearch.process;

import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchWithArrayRequest;
import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchWithArrayResponse;
import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchWithArrayRows;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

public class CustomerSearchWithArrayProcess extends AbsProcess {
  @Override
  public CustomerSearchWithArrayResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerSearchWithArrayResponse customerResponse = (CustomerSearchWithArrayResponse) response;
    CustomerSearchWithArrayRequest customerRequest = (CustomerSearchWithArrayRequest) request;

    ArrayList<String> customers = customerRequest.customers;
    ArrayList<CustomerSearchWithArrayRows> lst = new ArrayList<>();
    if (customers != null && customers.size() > 0) customers.forEach(c -> {
      Query query = new Query().addCriteria(Criteria.where("_id").is(new ObjectId(c)));
      CustomerSearchWithArrayRows result = mongoTemplate.findOne(query, CustomerSearchWithArrayRows.class, "Customer");
      if (result != null) lst.add(result);
    });
    customerResponse.rows = lst;
    return customerResponse;
  }

  public CustomerSearchWithArrayResponse createNewResponse() {
    return new CustomerSearchWithArrayResponse();
  }
}
