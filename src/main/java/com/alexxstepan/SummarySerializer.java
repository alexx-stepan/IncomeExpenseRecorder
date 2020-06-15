package com.alexxstepan;

import com.alexxstepan.entities.Summary;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class SummarySerializer extends StdSerializer<Summary> {

	protected SummarySerializer(StdSerializer<?> src) {
		super(src);
	}

	@Override
	public void serialize(Summary value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField(value.getCategoryName(), value.getValue());
		gen.writeEndObject();
	}
}
