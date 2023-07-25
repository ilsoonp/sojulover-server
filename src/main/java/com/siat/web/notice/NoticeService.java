package com.siat.web.notice;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional 
@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> noticeList() {
        return noticeRepository.findAll();
    }

    public void insertNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public Notice detailNotice(Long noticeId) {
        return noticeRepository.findById(noticeId).orElse(null);
    }

    public void increaseCount(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElse(null);
        if (notice != null) {
            int currentCount = notice.getViewCount();
            notice.setViewCount(currentCount + 1);
            noticeRepository.save(notice);
        }
    }

    public Notice getNoticeByNotice(Long noticeId) {
        return noticeRepository.findById(noticeId).orElse(null);
    }

    public void updateNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}