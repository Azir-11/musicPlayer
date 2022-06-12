package xyz.qcbyt.entity;

/**
 * 歌单从表
 */
public class PlayListSmall {
    private Integer userid;  //用户id
    private Integer playlistid; //歌单id

    public PlayListSmall(Integer userid, Integer playlistid) {
        this.userid = userid;
        this.playlistid = playlistid;
    }

    public PlayListSmall() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(Integer playlistid) {
        this.playlistid = playlistid;
    }

    @Override
    public String toString() {
        return "PlayListSmall{" +
                "userid=" + userid +
                ", playlistid=" + playlistid +
                '}';
    }
}
