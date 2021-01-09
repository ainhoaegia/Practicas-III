package Practicas5y6;

import java.util.*;

public class UsuarioTwitter {
	private String id; // identificador único de cada usuario
	private String screenName; // nick de twitter (sin la arroba)
	private ArrayList<String> tags; // lista de etiquetas
	private String avatar; // URL del gráfico del avatar de ese usuario
	private Long followersCount; // número de seguidores
	private Long friendsCount; // número de amigos
	private String lang; // idioma
	private Long lastSeen; // fecha de última entrada en twitter (en milisegundos desde 1/1/1970)
	private String tweetId; // identificador de tuit (ignorarlo)
	private ArrayList<String> friends; // lista de amigos (expresados como ids de usuarios)
	
	public UsuarioTwitter( String id, String screenName, ArrayList<String> tags, String avatar, 
			Long followersCount, Long friendsCount, String lang, Long lastSeen, String tweetId, ArrayList<String> friends) {
		this.id = id;
		this.screenName = screenName;
		this.tags = tags;
		this.avatar = avatar;
		this.followersCount = followersCount;
		this.friendsCount = friendsCount;
		this.lang = lang;
		this.lastSeen = lastSeen;
		this.tweetId = tweetId;
		this.friends = friends;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Long followersCount) {
		this.followersCount = followersCount;
	}

	public Long getFriendsCount() {
		return friendsCount;
	}

	public void setFriendsCount(Long friendsCount) {
		this.friendsCount = friendsCount;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Long getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Long lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public ArrayList<String> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<String> friends) {
		this.friends = friends;
	}
}
