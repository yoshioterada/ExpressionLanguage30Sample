package json.sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.KEY_NAME;


/*
 {
 "firstName": "Taro", "lastName": "Yamada", "age": 40,
 "phoneNumber": [
 { "type": "home", "number": "03-1234-5678" },
 { "type": "fax", "number": "090-9876-5432" }
 ]
 }
 */
public class JSONSample {

    private static final Logger logger = Logger.getLogger(JSONSample.class.getPackage().getName());

    public static void main(String[] args) {
        JSONSample sample = new JSONSample();
//        sample.getFirstNameFromFileSequencialParse();
//        sample.printJsonObject(createMostSimpleJsonObject());
//        sample.printJsonObject(createJsonObject());
//        System.out.println(createMostSimpleJsonStream(new StringWriter()).toString()); 
//        StringWriter sWriter = new StringWriter();
//        sWriter = (StringWriter) createJsonStream(sWriter);
//        System.out.println(sWriter.toString());
        try (BufferedReader br =
                new BufferedReader(new FileReader("/Users/tyoshio2002/json.dat"));
                JsonReader jsonReader = Json.createReader(br)) {
//            sample.parseJsonStream(br);
            sample.readJsonObject(jsonReader);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void printJsonObject(JsonObject jsonObject) {
        StringWriter sWriter = new StringWriter();
        try (JsonWriter writer = Json.createWriter(sWriter)) {
            writer.writeObject(jsonObject);
        }
        logger.log(Level.INFO, sWriter.toString());
        //情報: {} が出力
    }

    public static JsonObject createMostSimpleJsonObject() {
        JsonObject jsonObject = Json.createObjectBuilder().build();
        return jsonObject;
    }

    /*
     {
     "firstName": "Taro", "lastName": "Yamada", "age": 40,
     "phoneNumber": [
     { "type": "home", "number": "03-1234-5678" },
     { "type": "fax", "number": "090-9876-5432" }
     ]
     }
     */
    public static JsonObject createJsonObject() {
        JsonObject jsonObject = Json.createObjectBuilder(). // {
                add("firstName", "Taro"). //"firstName": "Taro"
                add("lastName", "Yamada"). //"lastName": "Yamada"
                add("age", 40). //"age": 40
                add("phoneNumber", //"phoneNumber": 
                Json.createArrayBuilder(). // [
                add(Json.createObjectBuilder(). //{
                add("type", "home"). //"type": "home"
                add("number", "03-1234-5678")). //"number": "03-1234-5678"}
                add(Json.createObjectBuilder(). //{
                add("type", "mobile"). //"type": "mobile"
                add("number", "090-9876-5432"))). //"number": "090-9876-5432" }
                build();
        return jsonObject;
    }

    public static Writer createMostSimpleJsonStream(Writer writer) {
        try (JsonGenerator generator = Json.createGenerator(writer)) {
            generator.
                    writeStartObject(). // {
                    writeEnd(); // }
        }
        return writer;
    }
    /*
     {
     "firstName": "Taro", "lastName": "Yamada", "age": 40,
     "phoneNumber": [
     { "type": "home", "number": "03-1234-5678" },
     { "type": "fax", "number": "090-9876-5432" }
     ]
     }
     */

    public static Writer createJsonStream(Writer writer) {
        try (JsonGenerator generator = Json.createGenerator(writer)) {
            generator.
                    writeStartObject().
                    write("firstName", "Taro").
                    write("lastName", "Yamada").
                    write("age", 40).
                    writeStartArray("phoneNumber").
                    writeStartObject().
                    write("type", "home").
                    write("number", "03-1234-5678").
                    writeEnd().
                    writeStartObject().
                    write("type", "mobile").
                    write("number", "090-9876-5432").
                    writeEnd().
                    writeEnd().
                    writeEnd();
        }
        return writer;
    }

    public void parseJsonStream(Reader reader) throws IOException {
        JsonParser parser = Json.createParser(reader);
        JsonParser.Event event;
        while (parser.hasNext()) {
            event = parser.next();

            switch (event) {
                case START_OBJECT: {
                    break;
                }
                case END_OBJECT: {
                    break;
                }
                case START_ARRAY: {
                    break;
                }
                case END_ARRAY: {
                    break;
                }
                case KEY_NAME: {
                    System.out.println("KEY_NAME: " + parser.getString());
                    break;
                }
                case VALUE_STRING: {
                    System.out.println("VALUE_STRING: " + parser.getString());
                    break;
                }
                case VALUE_NUMBER: {
                    System.out.println("VALUE_NUMBER: " + parser.getInt());
                    break;
                }
                case VALUE_TRUE: {
                    break;
                }
                case VALUE_FALSE: {
                    break;
                }
                case VALUE_NULL: {
                    break;
                }
            }
        }
    }

    public void readJsonObject(JsonReader jsonReader) {
        JsonObject jObj = jsonReader.readObject();
        if (jObj.containsKey("firstName")) {
            String value = jObj.getString("firstName");
            System.out.println(value); // Taro
        }
        if (jObj.containsKey("lastName")) {
            String value = jObj.getString("lastName");
            System.out.println(value); // Yamada
        }

        if (jObj.containsKey("phoneNumber")) {
            JsonArray array = jObj.getJsonArray("phoneNumber");
            for (JsonValue phoneValue : array) {
                if (phoneValue.getValueType() == JsonValue.ValueType.OBJECT) {
                    JsonObject arrayElem = array.getJsonObject(array.indexOf(phoneValue));
                    if (arrayElem.containsKey("type")) {
                        JsonString key = arrayElem.getJsonString("type");
                        System.out.println(key.getString());
                    }
                    if (arrayElem.containsKey("number")) {
                        JsonString number = arrayElem.getJsonString("number");
                        System.out.println(number.getString());
                    }
                }
            }
        }
    }
}
