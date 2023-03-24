package com.example.server.repo;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.mongodb.client.result.UpdateResult;

import com.example.server.models.Task;


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

    public void updateTaskById(String taskId, Task t) {

        ObjectId _id = new ObjectId(taskId);

        Criteria criteria = Criteria.where("_id").is(_id);
        Query query = Query.query(criteria);

        Update update = new Update();
        update.set("description", t.getDescription());
        update.set("due", t.getDue());
        update.set("priority", t.getPriority());
        UpdateResult updateResult = template.updateFirst(query, update, "tasks");

        if (updateResult == null)
            System.out.println("not updated");
        else 
            System.out.println(updateResult.getModifiedCount() + " document(s) updated..");
    }

    public Task findTaskById(String taskId) {
        ObjectId _id = new ObjectId(taskId);
        Criteria criteria = Criteria.where("_id").is(_id);
        Query query = Query.query(criteria);

        List<Task> results = template.find(query, Task.class, "tasks");
        return results.get(0);
    }
    
}
