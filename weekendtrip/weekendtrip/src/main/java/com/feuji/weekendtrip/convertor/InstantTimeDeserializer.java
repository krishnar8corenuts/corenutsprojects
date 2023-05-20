package com.feuji.weekendtrip.convertor;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class InstantTimeDeserializer extends JsonDeserializer<Instant> {

	@Override
	public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		return Instant.ofEpochMilli(p.getValueAsLong());
	}

}
