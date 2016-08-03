package com.feng.mp4ba.model;


public class FilmData {

	private Long id;
	private String filmName;
	private String onShownDate; // 电影上线日期
	private String showUrl;
	private String hashCode;
	private String torrentUrl;
	private String pubTime; // 网站发布资源时间
	
	
	
	private String filmSize;
	
	private String downloadCount;
	
	private FilmCategory category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}


	public String getOnShownDate() {
		return onShownDate;
	}

	public void setOnShownDate(String onShownDate) {
		this.onShownDate = onShownDate;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getTorrentUrl() {
		return torrentUrl;
	}

	public void setTorrentUrl(String torrentUrl) {
		this.torrentUrl = torrentUrl;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public String getFilmSize() {
		return filmSize;
	}

	public void setFilmSize(String filmSize) {
		this.filmSize = filmSize;
	}

	public String getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}

	public FilmCategory getCategory() {
		return category;
	}

	public void setCategory(FilmCategory category) {
		this.category = category;
	}

	
	
	
	
	
	
}
