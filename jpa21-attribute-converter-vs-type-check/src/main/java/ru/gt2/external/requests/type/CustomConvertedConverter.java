package ru.gt2.external.requests.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CustomConvertedConverter implements AttributeConverter<CustomConverted, String> {
    @Override
    public String convertToDatabaseColumn(CustomConverted attribute) {
        return Integer.toString(attribute.value);
    }

    @Override
    public CustomConverted convertToEntityAttribute(String dbData) {
        return new CustomConverted(Integer.parseInt(dbData));
    }
}
