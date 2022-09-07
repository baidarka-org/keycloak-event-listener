package base;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaProperties {
    public static final String SERVER = "kafka:9092";
    public static final String SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String RETRIES = "3";
    public static final String ACKS = "all";
    public static final String TOPIC = "REGISTER";
}
