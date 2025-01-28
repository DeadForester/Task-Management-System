package dev.api.converter;

import dev.api.enums.Priority;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class PriorityConverter implements Converter<Priority, Integer> {
    @Override
    public Integer convert(MappingContext<Priority, Integer> context) {
        return context.getSource() == null ? null : context.getSource().getId();
    }
}

