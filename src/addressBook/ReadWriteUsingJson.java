package addressBook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

public class ReadWriteUsingJson {
	 //ObjectMapper mapper = new ObjectMapper();
	 /*public void writeJson(String Filename,ArrayList<Person> addressbook) throws IOException {
	     String path=Filename+".json";
	     mapper.writeValue(new File(path),addressbook);
	 }*/
	    /*public void writeJson(String Filename,ArrayList<PersonInfo> addressbook) throws IOException {
	        String path=Filename+".json";
	        mapper.writeValue(new File(path),addressbook);
	    }
	    public ArrayList<PersonInfo> read(String Filename) throws Exception {
	        ArrayList<PersonInfo> addressbook=new ArrayList<>();
	        String path="C:\\Users\\PC\\rupika\\ADDRESSBOOK1\\src\\"+Filename+".json";
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(path));
	            String arrayToJson;
	            if ((arrayToJson = reader.readLine()) != null) {
	                TypeReference<ArrayList<PersonInfo>> type = new TypeReference<ArrayList<PersonInfo>>() {
	                };
	                addressbook=objectMapper.readValue(arrayToJson, type);
	                reader.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return addressbook;
	    }*/
}
