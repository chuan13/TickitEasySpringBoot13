package com.eeit87t3.tickiteasy.cwdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eeit87t3.tickiteasy.categoryandtag.entity.CategoryEntity;
import com.eeit87t3.tickiteasy.categoryandtag.entity.TagEntity;
import com.eeit87t3.tickiteasy.categoryandtag.service.CategoryService;
import com.eeit87t3.tickiteasy.categoryandtag.service.TagService;
import com.eeit87t3.tickiteasy.cwdfunding.entity.FundProj;
import com.eeit87t3.tickiteasy.cwdfunding.entity.FundProjDTO;
import com.eeit87t3.tickiteasy.cwdfunding.service.FundProjService;

@Controller
@RequestMapping("/fundprojects")
public class UserFundProjViewController {

	@Autowired
	private FundProjService projService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;
	
	@GetMapping()
	public String showProjPage(@RequestParam(value = "p", defaultValue = "1") Integer pageNumber, @RequestParam(required = false) Integer categoryID, Model model) {
		Integer pageSize = 9;
		Page<FundProjDTO> page = projService.findFundProjByPage(pageNumber, pageSize, categoryID);
		List<CategoryEntity> categories = categoryService.findFundProjCategoryList();
		List<TagEntity> tags = tagService.findFundProjTagList();

		model.addAttribute("page", page);
		model.addAttribute("categories", categories);
		model.addAttribute("tags", tags);
		model.addAttribute("selectedCategory",categoryID != null ? categoryID : 0);

		return "cwdfunding/cust_showFundProj";
		
	}
	
	@GetMapping("/{projectID}")
	public String showOneProjPage(@PathVariable Integer projectID, Model model) {
		FundProjDTO fundProjDTO = projService.findFundProjDTOById(projectID);
		
		model.addAttribute("fundProjDTO",fundProjDTO);
		
		return "cwdfunding/cust_showOneFundProj";
	}
	
	
}
