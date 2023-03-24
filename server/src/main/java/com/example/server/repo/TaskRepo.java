package com.example.server.repo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class TaskRepo {


    @Autowired
    private MongoTemplate template;

    public List<Document> findAllTasks() {
        Query query =  new Query();
        return template.find(query, Document.class, "tasks");
    }

    public Document insertTask(Document doc) {

        return template.insert(doc, "tasks");
    }
    
}
