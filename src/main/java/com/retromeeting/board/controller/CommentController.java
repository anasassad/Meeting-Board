package com.retromeeting.board.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.retromeeting.board.entity.Comment;
import com.retromeeting.board.enums.CommentType;
import com.retromeeting.board.service.CommentService;

import antlr.StringUtils;

@Controller
public class CommentController {
	private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
	
	private final CommentService commentService ;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService ;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("time",new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
		
		List<Comment> comments = commentService.getAllCommentsForToday();
		
		Map<CommentType, List<Comment>> groupedComments =
				comments.stream().collect(Collectors.groupingBy(Comment::getType));
		
		model.addAttribute("deltaComments",groupedComments.get(CommentType.DELTA));
		model.addAttribute("plusComments",groupedComments.get(CommentType.PLUS));
		model.addAttribute("starComments",groupedComments.get(CommentType.STAR));
		
		return "comment";
	}
	
	@PostMapping("/comment")
	public String createComment(
			@RequestParam(name = "plusComment" , required = false) String plusComment,
			@RequestParam(name = "starComment" , required = false) String starComment,
			@RequestParam(name = "deltaComment" , required = false) String deltaComment) {
		
		List<Comment> comments = new ArrayList<>();
		
		if (starComment != null && !starComment.isEmpty()) {
			comments.add(getComment(starComment,CommentType.STAR));
		}
		if (deltaComment != null && !deltaComment.isEmpty()) {
			comments.add(getComment(deltaComment,CommentType.DELTA));
		}
		if (plusComment != null && !plusComment.isEmpty()) {
			comments.add(getComment(plusComment,CommentType.PLUS));
		}
		
		if(!comments.isEmpty()) {
			LOGGER.info("SAVED {}",commentService.saveAll(comments));
		}
		return "redirect:/";
	}

	private Comment getComment(String comment, CommentType type) {
		Comment objComment = new Comment();
		objComment.setType(type);
		objComment.setComment(comment);
		return objComment;
	}
	
}
