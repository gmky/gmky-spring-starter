package gmky.core.mapper;

import java.time.*;
import java.util.List;

public interface EntityMapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDto(List<E> entityList);

    List<E> toEntity(List<D> dtoList);

    default LocalDateTime toLocalDateTime(Instant instant) {
        if (instant == null) return null;
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    default Instant toInstant(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.toInstant(ZoneOffset.UTC);
    }

    default OffsetDateTime toOffsetDateTime(Instant instant) {
        if (instant == null) return null;
        return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    default Instant toInstant(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) return null;
        return offsetDateTime.toInstant();
    }
}
