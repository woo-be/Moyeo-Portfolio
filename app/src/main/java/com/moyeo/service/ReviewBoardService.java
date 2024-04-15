package com.moyeo.service;

import com.moyeo.vo.ReviewBoard;
import com.moyeo.vo.ReviewPhoto;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewBoardService {

  void add(ReviewBoard reviewBoard);
  void increaseViews(int reviewBoardId);

  List<ReviewBoard> reviewList(int memberId, int pageSize, int pageNo);
  List<ReviewBoard> list();
  List<ReviewBoard> list(int pageNo, int pageSize);
  List<ReviewBoard> list(int pageNo, int pageSize, int regionId);
  ReviewBoard get(int reviewBoardId);

  int delete(int reviewBoardId);
  int countAll();
  int countAll(int regionId);
  int countPostedByMember(int memberId);
  int update(ReviewBoard reviewBoard);

  List<ReviewPhoto> getReviewPhotos(int reviewBoardId);
  ReviewPhoto getReviewPhoto(int reviewPhotoId);

  int deleteReviewPhoto(int reviewPhotoId);

  List<ReviewBoard> scrapList(int memberId, int pageNo, int pageSize);

  int countScrapByMember(@Param("memberId")int memberId);
}
