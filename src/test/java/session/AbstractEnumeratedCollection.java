package session;

public interface AbstractEnumeratedCollection<T> {
    Object get(final T key);

    default String getS(final T key) {
        Object value = get(key);
        return value == null ? null : value.toString();
    }

    default Integer getI(final T key) {
        Object value = get(key);
        return value == null ? null : Integer.parseInt(value.toString());
    }

    default Long getL(final T key) {
        Object value = get(key);
        return value == null ? null : Long.parseLong(value.toString());
    }
}
