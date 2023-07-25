package com.siat.web.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/noticeList")
    public ModelAndView noticeList() {
        List<Notice> noticeList = noticeService.noticeList();
        return new ModelAndView("noticeList", "noticeList", noticeList);
    }

    @GetMapping("/insertNotice")
    public String insertNoticeView() {
        return "insertNotice";
    }

    @PostMapping("/insertNotice")
    public String insertNotice(@ModelAttribute("notice") Notice notice) {
        noticeService.insertNotice(notice);
        return "redirect:noticeList";
    }

    @GetMapping("/detailNotice")
    public ModelAndView detailNoticeView(@RequestParam(name = "notice_id") Long noticeId) {
        Notice retrievedNotice = noticeService.detailNotice(noticeId);
        noticeService.increaseCount(noticeId);
        return new ModelAndView("detailNotice", "notice", retrievedNotice);
    }

    @GetMapping("/modifyNotice")
    public ModelAndView modifyNoticeView(@RequestParam(name = "notice_id") Long noticeId) {
        Notice notice = noticeService.getNoticeByNotice(noticeId);
        return new ModelAndView("modifyNotice", "notice", notice);
    }

    @PostMapping("/modifyNotice")
    public String updateNotice(@ModelAttribute("notice") Notice notice) {
        noticeService.updateNotice(notice);
        return "redirect:/noticeList";
    }

    @GetMapping("/deleteNotice")
    public String deleteNotice(@RequestParam(name = "notice_id") Long noticeId) {
        noticeService.deleteNotice(noticeId);
        return "forward:/noticeList";
    }
}