//package mis.util;
//import java.io.IOException;
//
//import org.codehaus.jackson.JsonGenerator;
//import org.codehaus.jackson.JsonProcessingException;
//import org.codehaus.jackson.map.SerializerProvider;
//import org.joda.time.DateTime;
//
//public final class DurationSerializer extends StdScalarSerializer<DateTime> {
//    public DurationSerializer() { super(DateTime.class); }
//
//    @Override
//    public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
//        JsonProcessingException {
//        jgen.writeNumber(value.getMillis());
//    }
//}

