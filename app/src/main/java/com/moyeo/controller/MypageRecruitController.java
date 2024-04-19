package com.moyeo.controller;

import com.moyeo.service.RecruitBoardService;
import com.moyeo.service.RecruitMemberService;
import com.moyeo.vo.Member;
import com.moyeo.vo.RecruitBoard;
import com.moyeo.vo.RecruitMember;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("myrecruit")
public class MypageRecruitController {

  private final static Log log = LogFactory.getLog(MypageRecruitController.class);
  private final RecruitBoardService recruitBoardService;
  private final RecruitMemberService recruitMemberService;

  @GetMapping("posted")
  public void mypost(Model model, HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하시기 바랍니다.");
    }
    model.addAttribute("mypost", recruitBoardService.mypost(loginUser.getMemberId()));
  }

  @GetMapping("/appl")
  public void appllist(Model model, int recruitBoardId, HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하시기 바랍니다.");
    }

    RecruitBoard recruitBoard = recruitBoardService.get(recruitBoardId);
    if (recruitBoard.getWriter().getMemberId() != loginUser.getMemberId()) {
      throw new Exception("권한이 없습니다.");
    }

    List<RecruitMember> list = recruitMemberService.findAllApplicant(recruitBoardId);
    for (RecruitMember recruitMember : list) {
      log.debug("recruitMember: "+ recruitMember);
    }

    model.addAttribute("applicants", list);
  }

  @GetMapping("reqpost")
  public void myrequest(Model model, HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하시기 바랍니다.");
    }
    model.addAttribute("myrequest", recruitBoardService.myrequest(loginUser.getMemberId()));
  }
}
