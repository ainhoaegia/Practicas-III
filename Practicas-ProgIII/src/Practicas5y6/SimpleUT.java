package Practicas5y6;

import java.io.*;
import java.util.*;

public class SimpleUT {

	private ArrayList<UsuarioTwitter> ut;
	
	public SimpleUT() {
		ut = new ArrayList<UsuarioTwitter>();
	}
	
	public void add(UsuarioTwitter sb) {
		ut.add(sb);
	}
	
	public void readFromCSV(String filename) {
		File file = new File(filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		BufferedReader infile = new BufferedReader(reader);
		String line = "";
		try {
			boolean done = false;
			while (!done) {
				line = infile.readLine();
				if (line == null) {
					done = true;
				} else {
					String[] tokens = line.trim().split(",");
					
					String id=null;
					String screenName=null;
					ArrayList<String> tags=null;
					String avatar=null;
					Long followersCount=null;
					Long friendsCount=null;
					String lang=null;
					Long lastSeen=null;
					String tweetId=null;
					ArrayList<String> friends=null;

					try {
						
						id = tokens[0].trim();
						screenName = tokens[1].trim();
						tags = new ArrayList<String>( Arrays.asList( tokens[2].trim() ) );
						avatar = tokens[3].trim();
						followersCount = Long.parseLong( tokens[4].trim() );
						friendsCount = Long.parseLong( tokens[5].trim() );
						lang = tokens[6].trim();
						lastSeen = Long.parseLong( tokens[7].trim() );
						tweetId = tokens[8].trim();
						friends = new ArrayList<String>( Arrays.asList( tokens[9].trim() ) );

					} catch (NumberFormatException e) {}
					
					UsuarioTwitter sb = new UsuarioTwitter(id, screenName, tags, avatar, followersCount, friendsCount, lang, lastSeen, tweetId, friends);
					ut.add(sb);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public Object[][] convert2Data() {
		Object[][] data = new Object[ut.size()][10];
		for (int i = 0; i < ut.size(); i++) {
			data[i][0] = ut.get(i).getId();
			data[i][1] = ut.get(i).getScreenName();
			data[i][2] = ut.get(i).getTags();
			try {
				data[i][3] = ut.get(i).getAvatar();
				data[i][4] = ut.get(i).getFollowersCount();
				data[i][5] = ut.get(i).getFriendsCount();
				data[i][6] = ut.get(i).getLang();
				data[i][7] = ut.get(i).getLastSeen();
				data[i][8] = ut.get(i).getTweetId();
				data[i][9] = ut.get(i).getFriends();
			} catch (ArrayIndexOutOfBoundsException e) { e.printStackTrace(); }		
		}
		return data;
	}
}
