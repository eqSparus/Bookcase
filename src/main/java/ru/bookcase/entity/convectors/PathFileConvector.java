package ru.bookcase.entity.convectors;

import javax.persistence.AttributeConverter;
import java.nio.file.Path;

public class PathFileConvector implements AttributeConverter<Path, String> {
    @Override
    public String convertToDatabaseColumn(Path attribute) {
        return attribute.toString();
    }

    @Override
    public Path convertToEntityAttribute(String dbData) {
        return Path.of(dbData);
    }
}
