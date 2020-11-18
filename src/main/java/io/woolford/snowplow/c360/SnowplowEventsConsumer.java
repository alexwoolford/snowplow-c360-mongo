package io.woolford.snowplow.c360;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class SnowplowEventsConsumer {

    private final Logger LOG = LoggerFactory.getLogger(SnowplowEventsConsumer.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @KafkaListener(topics="snowplow-enriched-json-good")
    public void consumeSnowplowEvents(ConsumerRecord<String, String> snowplowEvent) throws JsonProcessingException, InterruptedException {

        //TODO: remove network ID from nested elements.

        LOG.info(snowplowEvent.value());

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SnowplowEventRecord snowplowEventRecord = mapper.readValue(snowplowEvent.value(), SnowplowEventRecord.class);

        String id = snowplowEventRecord.getNetworkUserId();

        Update update = new Update();
        update.push("pageviews", snowplowEventRecord);
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query();
        query.addCriteria(criteria);

        mongoTemplate.upsert(query, update, "pageviews");

    }

}
