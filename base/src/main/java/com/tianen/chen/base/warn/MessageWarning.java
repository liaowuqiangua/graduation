package com.tianen.chen.base.warn;

/**
*  短信预警设置
*/

public class MessageWarning{

    
    /**
    *  交易日  -  key=[yes]
    *   WarningNotice-预警通知-1
    *   LiquidNotice-强平通知-2
    */
    private String messageTarget = "-";
    
    
    /**
    *  席位号  -  key=[yes]
    *   Remind-提醒-1
    *   Warn-警告-2
    *   Serious-严重-3
    */
    private String noticeLevel = "-";
    
    
    /**
    *  币种  -  key=[no]
    */
    private String message = "-";

    public MessageWarning(String messageTarget, String noticeLevel, String message) {
        this.messageTarget = messageTarget;
        this.noticeLevel = noticeLevel;
        this.message = message;
    }

    public MessageWarning() {
    }


    public String getMessageTarget() {
        return messageTarget;
    }

    public void setMessageTarget(String messageTarget) {
        this.messageTarget = messageTarget;
    }

    public String getNoticeLevel() {
        return noticeLevel;
    }

    public void setNoticeLevel(String noticeLevel) {
        this.noticeLevel = noticeLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageWarning{");
        sb.append("messageTarget='").append(messageTarget).append('\'');
        sb.append(", noticeLevel='").append(noticeLevel).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
