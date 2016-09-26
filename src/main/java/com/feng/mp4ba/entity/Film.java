package com.feng.mp4ba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.feng.mp4ba.utils.App;

@Entity
@Table(name = App.WI+"_film",schema=App.DB)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@SequenceGenerator(name = App.WAN+"_film", sequenceName = App.WAN+"_film_seq", allocationSize = 1)
public class Film extends BaseEntity {

	private static final long serialVersionUID = 6267610484428370095L;

	private Long id;
	private String filmName;
	private String onShowDate; // 电影上线日期
	private String showUrl;
	private String hashCode; // 唯一标志
	private String torrentUrl;
	private String pubTime; // 网站发布资源时间
	
	private String definition; // 清晰度	HD720P、HD1080P、BD720P等清晰度
	
	
	private String filmSize;
	
	private String downloadCount;
	
	private String imgUrl;
	
	
	private  Category cat;

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator=App.WAN+"_film")
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="film_name")
	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	@Column(name="onshow_date")
	public String getOnShowDate() {
		return onShowDate;
	}

	public void setOnShowDate(String onShowDate) {
		this.onShowDate = onShowDate;
	}

	@Column(name="showurl")
	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	@Column(name="hashcode")
	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	@Column(name="torrent_url")
	public String getTorrentUrl() {
		return torrentUrl;
	}

	public void setTorrentUrl(String torrentUrl) {
		this.torrentUrl = torrentUrl;
	}

	@Column(name="pub_time")
	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	@Column(name="definition")
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Column(name="film_size")
	public String getFilmSize() {
		return filmSize;
	}

	public void setFilmSize(String filmSize) {
		this.filmSize = filmSize;
	}

	@Column(name="download_count")
	public String getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category")
	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	@Column(name="img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
	
	
}
