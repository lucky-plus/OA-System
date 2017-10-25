package com.oa.activiti.utils;


import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException
;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.oa.activiti.entity.formParamList;

public class mongoDBOp {
    // create codec registry for POJOs
    static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    
	static Logger LOGGER = LoggerFactory.getLogger(mongoDBOp.class);
	
	static MongoClient mongoClient = new MongoClient("localhost", 27017);

	static MongoDatabase mdb = mongoClient.getDatabase("test_first_Demo").withCodecRegistry(pojoCodecRegistry);

	static MongoCollection<formParamList> docCol = mdb.getCollection("formData1", formParamList.class);

	public static void save(formParamList lp) throws JsonProcessingException {	
		docCol.insertOne(lp);
	}
	public static FindIterable<formParamList> findAll(){
		return docCol.find();
	}
	public static FindIterable<formParamList> findOne(int id){
		return docCol.find(Filters.eq("formId", id));
	}
	public static FindIterable<formParamList> findOne(String key){
		return docCol.find(Filters.eq("keyName", key));
	}
}
