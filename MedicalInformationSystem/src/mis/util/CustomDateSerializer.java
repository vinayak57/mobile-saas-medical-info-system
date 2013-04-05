package mis.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CustomDateSerializer extends JsonSerializer<DateTime> {

	@Override
    public void serialize(DateTime value, JsonGenerator gen, 
                          SerializerProvider arg2)
        throws IOException, JsonProcessingException {
    	 DateTimeFormatter formatter = 
    	        DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    	
System.out.println("calling serialize");
        gen.writeString(formatter.print(value));
    }
    
}

