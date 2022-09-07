package base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static base.KafkaProperties.TOPIC;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RequiredArgsConstructor(access = PRIVATE)
public class RegisterProducer {
    private final KafkaConfig config;

    public static final class RegisterKeycloakUserIdProducerHolder {
        private static final RegisterProducer producer =
                new RegisterProducer(KafkaConfig.getInstance());
    }

    public static RegisterProducer getInstance() {
        return RegisterKeycloakUserIdProducerHolder.producer;
    }
    public void produce(String keycloakUserId) {
        try (final var producer = new KafkaProducer<String, String>(config.configure())) {
            final var producerRecord = new ProducerRecord<String, String>(
                    TOPIC, keycloakUserId);

            log.info("Produce {} to topic {}", keycloakUserId, TOPIC);

            producer.send(producerRecord, (metadata, e) -> {
                if (e != null) {
                    log.error("Produced message occurred exception {} \n Metadata: {}",
                            e.getMessage(), metadata);

                    throw new RuntimeException(e.getMessage());
                }
            });
        }
    }
}
