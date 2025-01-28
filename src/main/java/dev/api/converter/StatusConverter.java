package dev.api.converter;

import dev.api.enums.Status;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class StatusConverter implements Converter<Status, Integer> {
    @Override
    public Integer convert(MappingContext<Status, Integer> context) {
        return context.getSource() == null ? null : context.getSource().getId();
    }
}