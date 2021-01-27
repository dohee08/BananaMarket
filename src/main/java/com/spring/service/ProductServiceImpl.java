package com.spring.service;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.banana.dao.productDAO;
import com.banana.vo.dongneVO;
import com.banana.vo.productVO;

@Service("productService")
public class ProductServiceImpl {
	
	@Autowired
	private productDAO productDAO;
	
	public Object insert(Object vo) {
		String result="";
		productVO pvo = (productVO)vo;
		UUID uuid = UUID.randomUUID(); 
		
			System.out.println(pvo.getFile_list().length);
			
			for(CommonsMultipartFile file : pvo.getFile_list()) {
				//파일이 존재하면 nfile nsfile
				System.out.println(file.getOriginalFilename());
				
				if(pvo.getFile_list().length != 0 ) {
					pvo.setPfile(file.getOriginalFilename());
					pvo.setPsfile(uuid+"_"+file.getOriginalFilename());
				}	
				//DB저장 
				boolean dao_result = productDAO.getInsert(pvo);
			
				if(dao_result){
					//서버저장 --> upload 폴더에 저장(폴더위치)
					File file1 = new File(pvo.getSavepath()+pvo.getPsfile());
					
					try {	
						file.transferTo(file1);
						
					}catch (Exception e) {
						e.printStackTrace();
					}
					result= "redirect:/popularProduct.do";
				}else {
					result ="errorPage";
				}
			}
			
			return result;
		} 
	
	
	
	 public Object getList() {
		  ModelAndView mv = new ModelAndView();
		  ArrayList<productVO> list = productDAO.getProductList();
		  
			mv.addObject("list", list);
			mv.setViewName("/popularProduct/popularProduct");
			return mv;
	  }

	 public Object getContent(Object pid) {
		 ModelAndView mv = new ModelAndView();
		 productVO vo = productDAO.getProductContent((String)pid);
			mv.addObject("vo", vo);
			mv.setViewName("/popularProduct/productContent");
			return mv;
	 }
	 

	 public Object getUpdateContent(Object pid) {
		  ModelAndView mv = new ModelAndView();
			productVO vo = productDAO.getProductContent((String)pid);
			mv.addObject("vo", vo);
			mv.setViewName("/popularProduct/updatePage");
			return mv;
		  
	  }
	 
	 
	 public Object getUpdate(Object vo) {
			ModelAndView mv = new ModelAndView();
			boolean result = productDAO.getProductUpdate((productVO)vo);
			
			if(result) {
				//mv.setViewName("redirect:/productContent.do");
				mv.setViewName("/mypage/mypage_contract");
			}
			return mv;
		}
	 
	 /* 
	 * } public Object delete() {
	 * 
	 * }
	 */
}
