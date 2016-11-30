package com.test.mail.app.dao.entities;

import javax.persistence.*;
import java.io.File;

/**
 * Created by tigran on 11/13/16.
 */
@Entity
@Table(name="musics")
public class UserMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MUSIC_ID")
    private Long musicId;

    @Column(name = "MUSIC_NAME", nullable = false)
    private String musicName;

    @Column(name = "MUSIC_PATH", nullable = false)
    private String musicPath;

    @Column(name = "LIKES")
    private Long likes;

    @Column(name = "DIS_LIKES")
    private Long disLikes;

    public UserMusic() {

    }

    public UserMusic(String musicName, String musicPath) {
        File f = new File(musicPath);
        if(!f.exists() || f.isDirectory()) {
            throw new RuntimeException(
                    String.format("Invalid path of file(\"%s\")", musicPath));
        }
        this.musicName = musicName;
        this.musicPath = musicPath;
    }

    public Long getMusicId() {
        return musicId;
    }

    public void setMusicId(Long musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(Long disLikes) {
        this.disLikes = disLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMusic music = (UserMusic) o;

//        if (musicId != null ? !musicId.equals(music.musicId) : music.musicId != null) return false;
        if (musicName != null ? !musicName.equals(music.musicName) : music.musicName != null) return false;
        return  !(musicPath != null ? !musicPath.equals(music.musicPath) : music.musicPath != null);
//        if (likes != null ? !likes.equals(music.likes) : music.likes != null) return false;
//        return !(disLikes != null ? !disLikes.equals(music.disLikes) : music.disLikes != null);

    }

    @Override
    public int hashCode() {
        int result = musicId != null ? musicId.hashCode() : 0;
        result = 31 * result + (musicName != null ? musicName.hashCode() : 0);
        result = 31 * result + (musicPath != null ? musicPath.hashCode() : 0);
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (disLikes != null ? disLikes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserMusic{" +
                "musicId=" + musicId +
                ", musicName='" + musicName + '\'' +
                ", musicPath='" + musicPath + '\'' +
                ", likes=" + likes +
                ", disLikes=" + disLikes +
                '}';
    }
}
