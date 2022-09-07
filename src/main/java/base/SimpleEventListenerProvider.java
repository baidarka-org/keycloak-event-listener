package base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

import static lombok.AccessLevel.PRIVATE;
import static org.keycloak.events.EventType.REGISTER;

@Slf4j
@RequiredArgsConstructor(access = PRIVATE)
public class SimpleEventListenerProvider implements EventListenerProvider {
    public final static String ID = "register-listener";
    private final RegisterProducer producer;

    public static final class SimpleEventListenerProviderHolder {
        private static final SimpleEventListenerProvider provider =
                new SimpleEventListenerProvider(RegisterProducer.getInstance());
    }

    public static SimpleEventListenerProvider getInstance() {
        return SimpleEventListenerProviderHolder.provider;
    }

    @Override
    public void onEvent(Event event) {
        if(event.getType() == REGISTER) {
            log.debug("Catch a new event from realm {}", event.getRealmId());

            final var keycloakUserId = event.getUserId();

            producer.produce(keycloakUserId);
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
    }

    @Override
    public void close() {
    }
}
