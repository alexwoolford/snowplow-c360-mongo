# snowplow-c360-mongo

Customer 360 is a very popular use-case for streaming data.

This example creates profiles, in MongoDB, for each Snowplow `network_userid`.

![topology](img/snowplow-mongo-views.png)

To reset the consumer:

    kafka-consumer-groups --bootstrap-server localhost:9092 --group snowplow-c360 -reset-offsets --topic snowplow-enriched-json-good --to-earliest --execute
