package rs.fon.jgrass.util;

import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.fon.jgrass.domain.Member;

public class MemberJsonUtility {

	public static JsonArray serializeMembers(LinkedList<Member> members) {
		JsonArray membersArray = new JsonArray();

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);

			JsonObject memberJson = new JsonObject();
			memberJson.addProperty("id", m.getId());
			memberJson.addProperty("name", m.getFirstName());
			memberJson.addProperty("lastName", m.getLastName());

			membersArray.add(memberJson);
		}

		return membersArray;
	}

	public static LinkedList<Member> parseMembers(JsonArray membersJson) {
		LinkedList<Member> members = new LinkedList<Member>();

		for (int i = 0; i < membersJson.size(); i++) {
			JsonObject memberJson = (JsonObject) membersJson.get(i);

			Member m = new Member();
			m.setId(memberJson.get("id").getAsInt());
			m.setFirstName(memberJson.get("name").getAsString());
			m.setLastName(memberJson.get("lastName").getAsString());

			members.add(m);
		}

		return members;
	}
}
