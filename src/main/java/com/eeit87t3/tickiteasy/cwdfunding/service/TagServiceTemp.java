package com.eeit87t3.tickiteasy.cwdfunding.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeit87t3.tickiteasy.categoryandtag.entity.TagEntity;
import com.eeit87t3.tickiteasy.categoryandtag.repository.TagRepo;
import com.eeit87t3.tickiteasy.cwdfunding.entity.Tag;
import com.eeit87t3.tickiteasy.cwdfunding.repository.TagRepository;

/**
 * @author Chuan(chuan13)
 */
@Service
public class TagServiceTemp {

	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private TagRepository tagRepository;
	
//	/**
//	 * 取得 Event 功能的主題分類列表。
//	 * 
//	 * @return eventTagList - List&lt;TagEntity>：Event 功能的主題分類列表。
//	 */
//	public List<TagEntity> findEventTagList() {
//	    return tagRepo.findByTagStatus((short) 0);
//	}
//
//	/**
//	 * 取得 Product 功能的主題分類列表。
//	 * 
//	 * @return productTagList - List&lt;TagEntity>：Product 功能的主題分類列表。
//	 */
//	public List<TagEntity> findProductTagList() {
//	    return tagRepo.findByTagStatus((short) 0);
//	}
//
	/**
	 * 取得 CwdFunding 功能的主題分類列表。
	 * 
	 * @return fundProjTagList - List&lt;TagEntity>：CwdFunding 功能的主題分類列表。
	 */
	public List<Tag> findFundProjTagList() {
	    return tagRepository.findByTagStatus((short) 0);
	}
//
//	/**
//	 * 取得 Post 功能的主題分類列表。
//	 * 
//	 * @return postTagList - List&lt;TagEntity>：Post 功能的主題分類列表。
//	 */
//	public List<TagEntity> findPostTagList() {
//	    List<TagEntity> postTagList = new ArrayList<>();
//	    postTagList.addAll(tagRepo.findByTagStatus((short) 0));
//	    postTagList.addAll(tagRepo.findByTagStatus((short) 1));
//	    return postTagList;
//	}

}
