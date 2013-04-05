//package mis.util;
//
//import java.text.SimpleDateFormat;
//
//import javax.ws.rs.Produces;
//import javax.ws.rs.ext.ContextResolver;
//import javax.ws.rs.ext.Provider;
//
//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
//
//@Provider
//@Produces("application/json")
//public class JacksonConfigurator implements ContextResolver<ObjectMapper> {
//    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
//            "yyyy-MM-dd HH:mm:ss");
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    public JacksonConfigurator() {
//        SerializationConfig serConfig = mapper.getSerializationConfig();
//        serConfig.setDateFormat(DATE_FORMAT);
//        DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
//        deserializationConfig.setDateFormat(DATE_FORMAT);
//        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//        //mapper.configure(new JodaModule);
//    }
//
//    public ObjectMapper getContext(Class<?> arg0) {
//        return mapper;
//    }
//
//}
