package com.retromeeting.board.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retromeeting.board.entity.Comment;
import com.retromeeting.board.repository.CommentRepo;

@Service
@Transactional(readOnly = true)
public class CommentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
	private final CommentRepo commentRepo ;
	
	public CommentService(CommentRepo commentRepo) {
		this.commentRepo = commentRepo ;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<Comment> saveAll(List<Comment> comments){
		
		LOGGER.info("Saving -> {}",comments);
		
		return commentRepo.saveAll(comments);
	}
	
	public List<Comment> getAllCommentsForToday(){
		LocalDate now = LocalDate.now();
		
		return commentRepo.findByCreatedYearAndMonthAndDay(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
	}

}
