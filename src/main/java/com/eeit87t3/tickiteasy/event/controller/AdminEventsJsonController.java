package com.eeit87t3.tickiteasy.event.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeit87t3.tickiteasy.event.dto.EventWithTicketTypesDTO;
import com.eeit87t3.tickiteasy.event.dto.EventsDTO;
import com.eeit87t3.tickiteasy.event.entity.EventsEntity;
import com.eeit87t3.tickiteasy.event.service.AdminEventsService;

/**
 * @author Chuan(chuan13)
 */
@RestController
@RequestMapping("/admin/api/event")
public class AdminEventsJsonController {
	
	@Autowired
	private AdminEventsService eventsService;

//	@GetMapping
//	public Page<EventsEntity> findByDynamic(
//			@RequestParam(value = "p", defaultValue = "1") Integer pageNumber,
//			@RequestParam(value = "category-string", required = false) String categoryString,
//			@RequestParam(value = "tag-string", required = false) String tagString,
//			@RequestParam(value = "searching-time", required = false) LocalDateTime searchingTime
//			) {
//		return eventsService.findByDynamic(pageNumber, categoryString, tagString, searchingTime);
//	}
	
//	@GetMapping("/listing")
//	public List<EventsEntity> findByListingAndOnsale() {
//		return eventsService.findByListingAndOnsale();
//	}
	
	@GetMapping("/{eventID}")
	public EventWithTicketTypesDTO findById(@PathVariable Integer eventID) {
		return new EventWithTicketTypesDTO(eventsService.findById(eventID));
	}
	
	@PostMapping
	public ResponseEntity<?> create(@ModelAttribute EventsDTO createEventsDTO) {
		String validateCreateInput = eventsService.validateCreateInput(createEventsDTO);
		if ("輸入正確！".equals(validateCreateInput)) {
			return new ResponseEntity<EventsEntity>(eventsService.create(createEventsDTO), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(validateCreateInput, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{eventID}")
	public ResponseEntity<?> edit(@ModelAttribute EventsDTO editEventsDTO) {
		String validateEditInput = eventsService.validateEditInput(editEventsDTO);
		if ("輸入正確！".equals(validateEditInput)) {
			return new ResponseEntity<EventsEntity>(eventsService.edit(editEventsDTO), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(validateEditInput, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{eventID}/edit-status")
	public ResponseEntity<?> editStatus(@PathVariable Integer eventID,
			@RequestBody Map<String, Object> requestBody) {
		Short editStatus = ((Integer) requestBody.get("editStatus")).shortValue();
		switch (editStatus) {
			case 0:
			case 1:
				String validateEditStatus = eventsService.validateEditStatus(eventID, editStatus);
				if ("輸入正確！".equals(validateEditStatus)) {
					return new ResponseEntity<EventsEntity>(eventsService.editStatus(eventID, editStatus), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(validateEditStatus, HttpStatus.BAD_REQUEST);
				}
			case -1:
				String validateDeleteInput = eventsService.validateDeleteInput(eventID);
				if ("輸入正確！".equals(validateDeleteInput)) {
					return new ResponseEntity<Boolean>(eventsService.delete(eventID), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(validateDeleteInput, HttpStatus.BAD_REQUEST);
				}
			default:
				return new ResponseEntity<String>("輸入錯誤：狀態值錯誤。", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{eventID}")
	public ResponseEntity<?> delete(@PathVariable Integer eventID) {
		String validateDeleteInput = eventsService.validateDeleteInput(eventID);
		if ("輸入正確！".equals(validateDeleteInput)) {
			return new ResponseEntity<Boolean>(eventsService.delete(eventID), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(validateDeleteInput, HttpStatus.BAD_REQUEST);
		}
	}
}
