package org.andot.share.app.line.mapper;

import org.andot.share.app.line.core.domain.ComLineMessage;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MongoMessageMapper extends MongoRepository<ComLineMessage, String> {
    @Query(value = "{}", sort = "{ 'footer.timestamp' : -1 }")
    ComLineMessage getFirstRecord();

}
