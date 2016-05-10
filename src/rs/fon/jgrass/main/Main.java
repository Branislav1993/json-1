package rs.fon.jgrass.main;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import rs.fon.jgrass.domain.Member;
import rs.fon.jgrass.util.MemberJsonUtility;

public class Main {

	public static void main(String[] args) throws IOException {
		LinkedList<Member> members = new LinkedList<Member>();

		Member m1 = new Member();
		m1.setId(1);
		m1.setFirstName("Petar");
		m1.setLastName("Petrovic");
		members.add(m1);

		Member m2 = new Member();
		m2.setId(2);
		m2.setFirstName("Marko");
		m2.setLastName("Markovic");
		members.add(m2);

		Member m3 = new Member();
		m3.setId(3);
		m3.setFirstName("Milan");
		m3.setLastName("Milanovic");
		members.add(m3);

		// pretvaranje u JSON format
		JsonArray membersJson = MemberJsonUtility.serializeMembers(members);

		// cuvanje u fajl
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/members.json")));

			// kreiranje Gson objekta
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			// pretty print
			String membersString = gson.toJson(membersJson);

			out.println(membersString);
			System.out.println(membersString);
			out.close();
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}

		// ucitavanje is JSON formata
		FileReader reader = new FileReader("data/members.json");

		Gson gson = new GsonBuilder().create();
		JsonArray mJson = gson.fromJson(reader, JsonArray.class);
		LinkedList<Member> newMembers = MemberJsonUtility.parseMembers(mJson);

		for (int i = 0; i < newMembers.size(); i++) {
			System.out.println(newMembers.get(i));
		}

	}

}
