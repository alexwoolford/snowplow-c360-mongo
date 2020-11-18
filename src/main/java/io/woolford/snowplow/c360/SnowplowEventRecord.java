package io.woolford.snowplow.c360;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SnowplowEventRecord {

    @Id
    private String networkUserId;

    private Date deviceCreatedTimestamp;
    private String pageUrl;

    @JsonGetter("network_userid")
    public String getNetworkUserId() {
        return networkUserId;
    }

    public void setNetworkUserId(String networkUserId) {
        this.networkUserId = networkUserId;
    }

    @JsonGetter("dvce_created_tstamp")
    public Date getDeviceCreatedTimestamp() {
        return deviceCreatedTimestamp;
    }

    public void setDeviceCreatedTimestamp(Date deviceCreatedTimestamp) {
        this.deviceCreatedTimestamp = deviceCreatedTimestamp;
    }

    @JsonGetter("page_url")
    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

}
