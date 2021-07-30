package com.acheter.customer.controller.classifieds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.acheter.customer.beans.security.UserDetailsImpl;
import com.acheter.customer.dto.account.ServiceAreaDto;
import com.acheter.customer.dto.adstrading.CategoryDto;
import com.acheter.customer.dto.adstrading.ImageDto;
import com.acheter.customer.dto.post.AddressDto;
import com.acheter.customer.dto.post.ClassifiedDto;
import com.acheter.customer.dto.post.ClassifiedTypeDto;
import com.acheter.customer.dto.post.ContactDetailsDto;
import com.acheter.customer.dto.post.NewPostStatusDto;
import com.acheter.customer.form.classifieds.ClassifiedForm;
import com.acheter.customer.form.classifieds.PaymentDetailsForm;
import com.acheter.customer.service.adstrading.AdsTradingService;
import com.acheter.customer.service.classifieds.ClassifiedService;
import com.acheter.customer.service.store.StoreService;
import com.acheter.customer.util.AcheterCustomerConstants;

@Controller
@RequestMapping("/classified")
public class ClassifiedController {
	private final String FORM_CLASSIFIED = "classifiedForm";
	private final String VIEW_POST_CLASSIFIED = "post-classified";
	private final String VIEW_POST_CLASSIFIED_SUCCESS = "post-classified-success";
	private final String VIEW_POST_PAYMENT = "post-payment";

	@Autowired
	private StoreService storeService;
	@Autowired
	private ClassifiedService classifiedService;
	@Autowired
	private AdsTradingService adsTradingService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/new")
	public String showPostClassifiedFormPage(Model model) {
		ClassifiedForm classifiedForm = null;

		classifiedForm = new ClassifiedForm();
		model.addAttribute(FORM_CLASSIFIED, classifiedForm);
		return VIEW_POST_CLASSIFIED;
	}

	@PostMapping("/new")
	public String postClassified(@RequestParam("classifiedImageFile") MultipartFile classifiedImageFile,
			@ModelAttribute("classifiedForm") @Valid ClassifiedForm classifiedForm, BindingResult errors, Model model)
			throws IOException {
		List<ClassifiedTypeDto> classifiedTypeDtos = null;
		ContactDetailsDto contactDetailsDto = null;
		ClassifiedTypeDto classifiedTypeDto = null;
		NewPostStatusDto newPostStatusDto = null;
		Authentication authentication = null;
		ServiceAreaDto serviceAreaDto = null;
		ClassifiedDto classifiedDto = null;
		List<ImageDto> postImages = null;
		CategoryDto categoryDto = null;
		AddressDto addressDto = null;
		String loggedInUser = null;
		String emailAddress = null;
		String classifiedTypeName = null;
		ImageDto imageDto = null;
		String mobileNo = null;
		Date expiryDate = null;
		Date today = null;
		Calendar cal = null;
		long fee = 0;
		String page = null;

		if (classifiedImageFile.isEmpty()) {
			model.addAttribute("classifiedImageFileError",
					messageSource.getMessage("NotNull.classifiedForm.classifiedImageFile", null, Locale.getDefault()));
			errors.reject("classifiedImageFile.blank");
		}
		if (errors.hasErrors()) {
			return VIEW_POST_CLASSIFIED;
		}
		try {
			today = new Date();
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 15);
			expiryDate = cal.getTime();

			authentication = SecurityContextHolder.getContext().getAuthentication();
			Object principle = authentication.getPrincipal();
			if (principle instanceof UserDetails) {
				loggedInUser = String.valueOf(((UserDetailsImpl) principle).getUserAccountNo());
				mobileNo = ((UserDetailsImpl) principle).getMobileNo();
				emailAddress = ((UserDetailsImpl) principle).getUsername();
			}

			imageDto = new ImageDto();
			imageDto.setFileName(classifiedImageFile.getOriginalFilename());
			imageDto.setContentType(classifiedImageFile.getContentType());
			imageDto.setImageFileContent(classifiedImageFile.getBytes());
			imageDto.setCreatedBy(loggedInUser);
			imageDto.setLastModifiedBy(loggedInUser);
			imageDto.setCreatedDate(today);
			imageDto.setLastModifiedDate(today);
			postImages = new ArrayList<>();
			postImages.add(imageDto);

			categoryDto = new CategoryDto();
			categoryDto.setCategoryId(classifiedForm.getCategoryId());

			classifiedTypeDto = new ClassifiedTypeDto();
			classifiedTypeDto.setClassifiedTypeId(classifiedForm.getClassifiedTypeId());

			serviceAreaDto = new ServiceAreaDto();
			serviceAreaDto.setServiceAreaId(classifiedForm.getServiceAreaId());

			addressDto = new AddressDto();
			addressDto.setAddressLine1(classifiedForm.getAddressLine1());
			addressDto.setAddressLine2(classifiedForm.getAddressLine2());
			addressDto.setCity(classifiedForm.getCity());
			addressDto.setState(classifiedForm.getState());
			addressDto.setZip(classifiedForm.getZip());
			addressDto.setCountry(classifiedForm.getCountry());
			addressDto.setCreatedBy(loggedInUser);
			addressDto.setLastModifiedBy(loggedInUser);
			addressDto.setCreatedDate(today);
			addressDto.setLastModifiedDate(today);

			contactDetailsDto = new ContactDetailsDto();
			contactDetailsDto.setPrimaryContactNo(mobileNo);
			contactDetailsDto.setPrimaryEmailAddress(emailAddress);
			contactDetailsDto.setSecondaryContactNo(classifiedForm.getSecondaryContactNo());
			contactDetailsDto.setSecondaryEmailAddress(classifiedForm.getSecondaryEmailAddress());
			contactDetailsDto.setCreatedBy(loggedInUser);
			contactDetailsDto.setLastModifiedBy(loggedInUser);
			contactDetailsDto.setCreatedDate(today);
			contactDetailsDto.setLastModifiedDate(today);

			classifiedDto = new ClassifiedDto();
			classifiedDto.setTitle(classifiedForm.getTitle());
			classifiedDto.setDescription(classifiedForm.getDescription());
			classifiedDto.setExpiryDate(expiryDate);
			classifiedDto.setPostedDate(today);
			classifiedDto.setPostedBy(loggedInUser);
			classifiedDto.setPrice(classifiedForm.getPrice());

			classifiedDto.setAddress(addressDto);
			classifiedDto.setCategory(categoryDto);
			classifiedDto.setClassifiedTypeDto(classifiedTypeDto);
			classifiedDto.setContactDetails(contactDetailsDto);
			classifiedDto.setServiceArea(serviceAreaDto);
			classifiedDto.setCreatedBy(loggedInUser);
			classifiedDto.setLastModifiedBy(loggedInUser);
			classifiedDto.setCreatedDate(today);
			classifiedDto.setLastModifiedDate(today);
			classifiedDto.setPostImages(postImages);

			classifiedTypeDtos = classifiedService.getClassifiedTypes();
			for (ClassifiedTypeDto dto : classifiedTypeDtos) {
				if (dto.getClassifiedTypeId() == classifiedForm.getClassifiedTypeId()) {
					classifiedTypeName = dto.getClassifiedTypeName();
				}
			}

			model.addAttribute("title", classifiedForm.getTitle());
			model.addAttribute("classifiedType", classifiedTypeName);

			if (classifiedTypeName.equals(AcheterCustomerConstants.CLASSIFIED_TYPE_FREE)) {
				classifiedDto.setStatus(AcheterCustomerConstants.STATUS_POST_NEW);

				page = VIEW_POST_CLASSIFIED_SUCCESS;
			} else {
				if (classifiedTypeName.equals(AcheterCustomerConstants.CLASSIFIED_TYPE_PREMIUM)) {
					fee = 5000;
				} else {
					fee = 10000;
				}
				classifiedDto.setClassifiedFee(fee);
				classifiedDto.setStatus(AcheterCustomerConstants.STATUS_POST_PENDING);
				model.addAttribute("amount", fee);
				page = VIEW_POST_PAYMENT;
			}
			newPostStatusDto = classifiedService.saveClassified(classifiedDto);
			model.addAttribute("orderId", newPostStatusDto.getOrderId());
			model.addAttribute("emailAddress", emailAddress);
			model.addAttribute("mobileNo", mobileNo);
			model.addAttribute("postId", newPostStatusDto.getPostId());

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return page;
	}

	@PostMapping("/updatePaymentDetails")
	public String updatePaymentStatus(@ModelAttribute("paymentDetails") PaymentDetailsForm paymentDetailsForm) {
		classifiedService.updatePaymentStatus(paymentDetailsForm.getPaymentId(), paymentDetailsForm.getOrderId(),
				paymentDetailsForm.getPostId(), AcheterCustomerConstants.STATUS_POST_NEW);
		
		return VIEW_POST_CLASSIFIED_SUCCESS;
	}

	@GetMapping(value = "/{city}/serviceAreas", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<ServiceAreaDto> getServiceAreas(@PathVariable("city") String city) {
		return storeService.getServiceAreas(city);
	}

	@ModelAttribute("cities")
	public List<String> getCities() {
		return storeService.getCities();
	}

	@ModelAttribute("states")
	public List<String> getStates() {
		return storeService.getStates();
	}

	@ModelAttribute("categories")
	public List<CategoryDto> getCategories() {
		return adsTradingService.getCategories();
	}

	@ModelAttribute("classifiedTypes")
	public List<ClassifiedTypeDto> getClassifiedTypes() {
		return classifiedService.getClassifiedTypes();
	}

}
