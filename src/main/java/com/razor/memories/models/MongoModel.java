package com.razor.memories.models;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

/**
 * Created by paulhemmings on 12/3/15.
 */

public class MongoModel {

    @org.jongo.marshall.jackson.oid.Id
    @org.jongo.marshall.jackson.oid.ObjectId
    protected String id;

    protected MongoDateTime createDate;
    protected MongoDateTime updateDate;

    public String getId() {
        return id;
    }

    public MongoModel setId(String id) {
        this.id = id;
        return this;
    }

    public String newId() {
        return new ObjectId().toHexString();
    }

    public LocalDateTime getCreateDate() {
        return createDate.getLocalDateTime();
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = new MongoDateTime().setLocalDateTime(createDate);
    }
    public LocalDateTime getUpdateDate() {
        return updateDate.getLocalDateTime();
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = new MongoDateTime().setLocalDateTime(updateDate);
    }

    public static class MongoDateTime {
        private String localDateTime;
        public MongoDateTime() { this.localDateTime = LocalDateTime.now().toString(); }
        public LocalDateTime getLocalDateTime() { return LocalDateTime.parse(this.localDateTime); }
        public MongoDateTime setLocalDateTime(LocalDateTime dateTime) {
            this.localDateTime = dateTime.toString();
            return this;
        }
    }
}
