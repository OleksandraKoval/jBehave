package session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public final class TestSession implements AbstractEnumeratedCollection<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(TestSession.class);

    private static final String SKIPPED_IN_LOGS = "skipped in logs";
    private static final String GET_OBJECT_FROM_SESSION_MESSAGE = "Get object from Global Session by key: {}, object:" +
            " {}";
    private static final String PUT_OBJECT_INTO_SESSION_MESSAGE = "Put object into Global Session by key: {}, object:" +
            " {}";

    private static TestSession instance;
    private static Map<Object, Object> session;

    public static TestSession getInstance() {
        if (null == instance) {
            instance = new TestSession();
            session = new HashMap<>();
        }
        return instance;
    }

    @Override
    public Object get(final Object key) {
        return get(key, true);
    }

    public void put(final Object key, final Object value) {
        put(key, value, true);
    }

    public Object get(final Object key, final boolean isValueNeedToBeLogged) {
        Object foundValue = session.get(key);
        if (key instanceof SessionKey && !Objects.isNull(foundValue)) {
            LOG.info(GET_OBJECT_FROM_SESSION_MESSAGE, key,
                    isValueNeedToBeLogged ? foundValue : SKIPPED_IN_LOGS);
        }
        return foundValue;
    }

    public void put(final Object key, final Object value, final boolean isValueNeedToBeLogged) {
        LOG.info(PUT_OBJECT_INTO_SESSION_MESSAGE, key,
                isValueNeedToBeLogged ? value : SKIPPED_IN_LOGS);
        session.put(key, value);
    }
}