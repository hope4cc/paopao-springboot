package com.paopao.model.modelView;

import com.paopao.model.pojo.Notice;
import com.paopao.util.MyHttpStatus;

import java.util.List;

public class NoticeResponse extends BaseModelView {

    private List<Notice> notices;

    public NoticeResponse(MyHttpStatus status) {
        super(status);
    }

    public NoticeResponse(MyHttpStatus status, String msg) {
        super(status, msg);
    }

    public NoticeResponse(MyHttpStatus status, List<Notice> notices) {
        super(status);
        this.notices = notices;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }
}
