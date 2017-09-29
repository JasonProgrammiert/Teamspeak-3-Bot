import java.util.ArrayList;
import java.util.logging.Level;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class Main {
	public static final TS3Config config = new TS3Config();
	public static final TS3Query query = new TS3Query(config);
	public static final TS3Api api = query.getApi();
	public static final ArrayList<String> blacklist = new ArrayList<>();
	
	public static void main(String[] arg) {
		config.setHost("127.0.0.1");
    
		query.connect();
		
		api.login("BENUTZERNAME", "PASSWORT"); // LOGIN EINGEBEN
		api.selectVirtualServerById(1);
		api.setNickname("TS3-Bot");
		addWords();
		check.start();
		Events.loadEvents();
	}
	
	public static void addWords() {
		blacklist.add("arsch");
		blacklist.add("test");
	}
	public static void CheckName(Client c) {
		String name = c.getNickname().toLowerCase();
		if(blacklist.contains(name.toLowerCase())) {
			api.kickClientFromServer("[Bot][color=red] Checker-Bot: Unerlaubter Nickname", c);
		}
	}
	
	public static void CheckChannelName(String id, String name) {
		Client c = api.getClientByUId(id);
		if(blacklist.contains(name.toLowerCase())) {
			api.kickClientFromChannel(c);
		}
	}
}
