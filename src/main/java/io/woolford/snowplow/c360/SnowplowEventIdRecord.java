package io.woolford.snowplow.c360;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class SnowplowEventIdRecord extends SnowplowEventRecord {

    @Id
    private String networkUserId;

    @JsonGetter("network_userid")
    public String getNetworkUserId() {
        return networkUserId;
    }

    public void setNetworkUserId(String networkUserId) {
        this.networkUserId = networkUserId;
    }

}
