import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.restlet.data.CharacterSet;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class Main {
	public static void main(String[] args) throws JSONException, IOException {
		 JSONStringer jsRequest = new JSONStringer();
		 JSONStringer js = new JSONStringer();
		 try {
		 js.object();
		 jsRequest.object();
		 jsRequest.key("user_email").value("carlos@gmail.com");
		 jsRequest.key("login").value("machoAlfa");
		 //jsRequest.key(“score”).value(2.0);
		 jsRequest.endObject();
		 js.key("request").value(jsRequest);
		 js.endObject();
		 } catch (JSONException e1) {
		 // TODO Auto-generated catch block
		 e1.printStackTrace();
		 }
		 System.out.println(jsRequest.toString());
		 System.out.println(js.toString());
		 //ClientResource requestResource = new ClientResource("http://10-dot-com-silicon-letseat.appspot.com/singup");
		 ClientResource requestResource = new ClientResource("http://localhost:8888/singup");
		 Representation rep;
		 rep = new JsonRepresentation(js);
		 rep.setMediaType(MediaType.APPLICATION_JSON);
		 rep.setCharacterSet(CharacterSet.UTF_8);
		 //http://restlet.tigris.org/issues/show_bug.cgi?id=1219
		 Representation reply = requestResource.post(rep);
		 String replyText = reply.getText();
		 System.out.println("Reply Text:" + replyText);
		 System.out.println("Reply Media Type:" + reply.getMediaType());
		 reply.write(System.out);
		 if (reply.getMediaType().equals(new MediaType("application/json"))) {
		 JSONObject jsObj = new JSONObject(replyText);
		 	String code = jsObj.getString("code");
		 	String desc = jsObj.getString("desc");
		 	System.out.println("Code:" + code + ",DESC:" + desc);
		 }
		 reply.release();
	}
	
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}