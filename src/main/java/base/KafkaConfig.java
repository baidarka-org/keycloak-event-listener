package base;

import lombok.NoArgsConstructor;

import java.util.Properties;

import static base.KafkaProperties.SERVER;
import static base.KafkaProperties.SERIALIZER;
import static base.KafkaProperties.RETRIES;
import static base.KafkaProperties.ACKS;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;

@NoArgsConstructor(access = PRIVATE)
public class KafkaConfig {
    private static final class ConfigHolder {
        private static final KafkaConfig config = new KafkaConfig();
    }

    public static KafkaConfig getInstance() {
        return ConfigHolder.config;
    }

    public Properties configure() {
        var properties = new Properties();

        properties.put(BOOTSTRAP_SERVERS_CONFIG, SERVER);
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, SERIALIZER);
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, SERIALIZER);
        properties.put(RETRIES_CONFIG, RETRIES);
        properties.put(ACKS_CONFIG, ACKS);

        return properties;
    }
}
