package io.woolford.snowplow.c360;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "c360")
public class SnowplowEventRecordList extends SnowplowEventRecord {

    List<SnowplowEventRecord> snowplowEventRecordList;

    public List<SnowplowEventRecord> getSnowplowEventRecordList() {
        return snowplowEventRecordList;
    }

    public void setSnowplowEventRecordList(List<SnowplowEventRecord> snowplowEventRecordList) {
        this.snowplowEventRecordList = snowplowEventRecordList;
    }

}
