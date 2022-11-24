package com.retromeeting.board.entity;

import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.retromeeting.board.enums.CommentType;

import lombok.Data;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Table(name = "rb_comment")
@EntityListeners(value = AuditingEntityListener.class)
@Data
public class Comment {

	@Id
	@GeneratedValue
	private Long id ;
	
	private String comment ;
	
	@Enumerated(value = EnumType.STRING)
	private CommentType type ;
	
	@CreatedDate
	private Timestamp createdDate ;
	
	@CreatedBy
	private String createdBy ;
	
	
}
