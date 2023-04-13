package com.Nixagh.Learn.KCP.Authorized.AuthorizedProcess;

import com.Nixagh.Learn.KCP.Authorized.dto.AuthorizedResult;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AuthorizedProcess {
  @Autowired
  MongoTemplate mongoTemplate;

  public static Query getQuery(AbsRequest request) {
    String userId = request.accessInfo.userId;
    String token = request.accessInfo.token;
    String timeNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(new ObjectId(userId)));
    query.addCriteria(Criteria.where("token").is(token));
    query.addCriteria(Criteria.where("timestamp").gte(timeNow));
    return query;
  }

  public boolean authorize(AbsRequest request) {
    Query query = getQuery(request);
    AuthorizedResult result = mongoTemplate.findOne(query, AuthorizedResult.class, "Authentication");

    return result != null;
  }

  public boolean authorize(MongoTemplate mongoTemplate, AbsRequest request) {
    Query query = getQuery(request);
    AuthorizedResult result = mongoTemplate.findOne(query, AuthorizedResult.class, "Authentication");

    return result != null;
  }
}
