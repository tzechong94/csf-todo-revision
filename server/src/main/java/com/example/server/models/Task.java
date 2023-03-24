package com.example.server.models;

import java.io.StringReader;

import org.bson.Document;
import org.bson.types.ObjectId;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String description;
    private String priority;
    private String due;
    private ObjectId _id;

 
    public static Task create(Document doc) {
        Task task = new Task();

        task.setDescription(doc.getString("description"));
        task.setPriority(doc.getString("priority"));
        task.setDue(doc.getString("due"));
        return task;
    }

    public static Task createTask(JsonObject taskEntry) {
        Task t = new Task();
        t.setDescription(taskEntry.getString("description"));
        t.setPriority(taskEntry.getString("priority"));
        t.setDue(taskEntry.getString("due"));

        return t;
    }

    public Task createFromDoc(Document d) {
        Task t = new Task();
        t.set_id(d.getObjectId("_id"));
        t.setDescription(d.getString("description"));
        t.setPriority(d.getString("priority"));
        t.setDue(d.getString("due"));

        return null;
    }

    public Document toDoc() {
        Document doc = new Document();
        doc.put("description", getDescription());
        doc.put("due", getDue());
        doc.put("priority", getPriority());
        return doc;
    }

    public static Task toTask(String j) {
		JsonReader reader = Json.createReader(new StringReader(j));
		return toTask(reader.readObject());
	}

	public static Task toTask(JsonObject j) {
		Task task = new Task();
		// if (j.containsKey("description") && (!j.isNull("description")))
		task.setDescription(j.getString("description"));
		task.setDue(j.getString("due"));
		task.setPriority(j.getString("priority"));

		return task;
	}

}
