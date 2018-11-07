package cn.youai.ssm.po;

import java.io.Serializable;
import java.util.Date;

public class GameChatContent implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer sender;
    private Integer receiver;
    private Long chatId;
    private String content;
    private Date sendtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

	@Override
	public String toString() {
		return "GameChatContent [id=" + id + ", sender=" + sender
				+ ", receiver=" + receiver + ", chatId=" + chatId
				+ ", content=" + content + ", sendtime=" + sendtime + "]";
	}
    
    
}
