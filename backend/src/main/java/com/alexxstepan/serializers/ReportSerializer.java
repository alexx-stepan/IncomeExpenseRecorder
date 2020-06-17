package com.alexxstepan.serializers;

import com.alexxstepan.model.CategoryTotal;
import com.alexxstepan.model.Report;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

//@JsonComponent
public class ReportSerializer extends StdSerializer<Report> {

	public ReportSerializer() {
		this(null);
	}

	protected ReportSerializer(Class<Report> t) {
		super(t);
	}

	@Override
	public void serialize(Report value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();

		gen.writeObjectFieldStart("income");
		for (CategoryTotal categoryTotal : value.getTotalIncome()) {
			gen.writeStringField(categoryTotal.getCategoryName(), String.valueOf(categoryTotal.getValue()));
		}
		gen.writeEndObject();

		gen.writeObjectFieldStart("expense");
		for (CategoryTotal categoryTotal : value.getTotalExpense()) {
			gen.writeStringField(categoryTotal.getCategoryName(), String.valueOf(categoryTotal.getValue()));
		}
		gen.writeEndObject();

		gen.writeEndObject();
	}
}
