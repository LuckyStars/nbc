package com.xuechong.bootstraptests.service;

import java.util.List;

import com.xuechong.bootstraptests.model.Comment;

public interface CommentService {
	public void addComment(Comment comment);
	public List<Comment> list(int page);
	public int totalPage();
	public List<Comment> listByTopic(String topicId,Integer page);
	public int totalPageByTopic(String topicId);
}
