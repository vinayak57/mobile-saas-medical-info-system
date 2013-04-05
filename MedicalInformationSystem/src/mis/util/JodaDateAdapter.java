package mis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaDateAdapter extends XmlAdapter<String, DateTime> {
	private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String marshal(DateTime v) {
        return formatter.print(v);
    }

    @Override
    public DateTime unmarshal(String v) {
        try {
            return formatter.parseDateTime(v);
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }
}
