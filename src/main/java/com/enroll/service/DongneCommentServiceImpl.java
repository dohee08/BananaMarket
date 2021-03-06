package com.enroll.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.banana.dao.DongneCommentDAO;
import com.banana.dao.dongneDAO;
import com.banana.vo.BananaKeywordAlarmVO;
import com.banana.vo.BananaReviewAlarmVO;
import com.banana.vo.BananaShopAlarmVO;
import com.banana.vo.DongneCommentVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service("dongneCommentService")
public class DongneCommentServiceImpl implements EnrollService {
	
	/////
	@Autowired
	private DongneCommentDAO dongneCommentDAO;
	
	@Autowired
	private dongneDAO dongneDAO;
	/////
	
	@Override
	public String insertStore(Object vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String shopAlarmWrite(Object vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getShopId(String sid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getKeywordAlarmCount(String mid) {
		int val = dongneDAO.getKeywordAlarmCount(mid);
		return String.valueOf(val);
	}
	
	public String key_alarm_write(String mid) {
		boolean result = dongneDAO.keyAlarmWrite(mid);
		return String.valueOf(result);
	}
	
	public String deleteKeywordAlarm(String pid) {
		boolean result = dongneDAO.deleteKeywordAlarmProc(pid);
		return String.valueOf(result);
	}
	
	public String deleteShopAlarm(String srid) {
		boolean result = dongneDAO.deleteShopAlarmProc(srid);
		return String.valueOf(result);
	}
	
	public String deleteReviewAlarm(String brid) {
		boolean result = dongneDAO.deleteReviewAlarmProc(brid);
		return String.valueOf(result);
	}
	
	public String getKeywordAlarmContent(String mid) {
		ArrayList<BananaKeywordAlarmVO> list = dongneDAO.getKeywordContent(mid);
		
		JsonArray jarray = new JsonArray();
		JsonObject jdata = new JsonObject();
		Gson gson = new Gson();
		
		for(BananaKeywordAlarmVO vo : list) {
			JsonObject jobj = new JsonObject();
			jobj.addProperty("mid", vo.getMid());
			jobj.addProperty("pid", vo.getPid());
			jobj.addProperty("ptitle", vo.getPtitle());
			jobj.addProperty("keyword", vo.getKeyword());
			jarray.add(jobj);
		}
		jdata.add("jlist", jarray);
		
		return gson.toJson(jdata);
	}
	
	public String getShopAlarmContent(String mid) {
		ArrayList<BananaShopAlarmVO> salist = dongneDAO.getShopContent(mid);
		
		JsonArray jarray = new JsonArray();
		JsonObject jdata = new JsonObject();
		Gson gson = new Gson();
		
		for(BananaShopAlarmVO vo : salist) {
			JsonObject jobj = new JsonObject();
			jobj.addProperty("sname", vo.getSname());
			jobj.addProperty("mid", vo.getMid());
			jobj.addProperty("srcontent", vo.getSrcontent());
			jobj.addProperty("srid", vo.getSrid());
			jobj.addProperty("sa_date", vo.getSa_date());
			jobj.addProperty("sid", vo.getSid());
			jarray.add(jobj);
		}
		jdata.add("jlist", jarray);
		
		return gson.toJson(jdata);
	}
	
	public String getAlarmContent(String mid) {
		ArrayList<BananaReviewAlarmVO> ralist = dongneDAO.getReviewContent(mid);
		
		JsonArray jarray = new JsonArray();
		JsonObject jdata = new JsonObject();
		Gson gson = new Gson();
		
		for(BananaReviewAlarmVO vo : ralist) {
			JsonObject jobj = new JsonObject();
			jobj.addProperty("btopic", vo.getBtopic());
			jobj.addProperty("mid", vo.getMid());
			jobj.addProperty("bcomment", vo.getBcomment().replace("\r\n", "<br>"));
			jobj.addProperty("brid", vo.getBrid());
			jobj.addProperty("ra_data", vo.getRa_date());
			jobj.addProperty("bid", vo.getBid());
			jarray.add(jobj);
		}
		jdata.add("jlist", jarray);
		
		return gson.toJson(jdata);
	}
	
	public String getShopAlarmCount(String mid) {
		int count = dongneDAO.getShopAlarmCount(mid);
		return String.valueOf(count);
	}
	
	public String getAlarmCount(String mid) {
		int count = dongneDAO.getAlarmCount(mid);
		return String.valueOf(count);
	}
	
	public String reviewAlarmWrite(Object vo) {
		boolean result = false;
		BananaReviewAlarmVO rvo = (BananaReviewAlarmVO)vo;
		result = dongneDAO.reviewAlarmWrite(rvo); 
		return String.valueOf(result);
	}
	
	@Override
	public Object getList() {
		return null;
	}

	@Override
	public Object insert(Object vo) {
		ModelAndView mv = new ModelAndView();
		
		boolean result = false;
		DongneCommentVO dcvo = (DongneCommentVO)vo;
		result = dongneCommentDAO.insertDongneComment(dcvo);
		 
		if(result) {
			if(dcvo.getLoc().equals("subcontent")) {
				System.out.println(dcvo.getTitle());
				mv.setViewName("redirect:/mypage_subjectContent.do?bstitle="+dcvo.getTitle());

			}else {
				mv.setViewName("redirect:/dongneLife_content.do?bid="+dcvo.getBid());
				
			}
		}else {
			
		}
		
		return mv;
	}

	public Object insert1(Object vo) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		
		boolean result = false;
		DongneCommentVO dcvo = (DongneCommentVO)vo;
		result = dongneCommentDAO.insertDongneComment(dcvo);
		 String encodedParam = URLEncoder.encode(dcvo.getTitle(), "UTF-8");
		if(result) {
			if(dcvo.getLoc().equals("subcontent")) {
				mv.setViewName("redirect:/mypage_subjectContent.do?bstitle="+encodedParam);

			}else {
				mv.setViewName("redirect:/dongneLife_content.do?bid="+dcvo.getBid());
				
			}
		}else {
			
		}
		
		return mv;
	}
	@Override
	public Object getContent(Object id, String mid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object update(Object vo) {
		return "";
	}
	
	public Object update1(Object vo) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();

		boolean result = false;
		DongneCommentVO dcvo = (DongneCommentVO)vo;
		String encodedParam = URLEncoder.encode(dcvo.getTitle(), "UTF-8");
		String bid = dongneCommentDAO.getBid(dcvo);
		result = dongneCommentDAO.dongneCommentUpdate(dcvo);
		
		if(result) {
			if(!dcvo.getTitle().equals("null")) {
				mv.setViewName("redirect:/mypage_subjectContent.do?bstitle="+encodedParam);

			}else {
				mv.setViewName("redirect:/dongneLife_content.do?bid="+bid);
				
			}
		}else {
			
		}
		
		return mv;
	}

	@Override
	public Object getUpdateContent(Object brid) {
		ModelAndView mv = new ModelAndView();
		DongneCommentVO vo = new DongneCommentVO();
		String bid = dongneCommentDAO.getBid(vo);
		
		vo = dongneCommentDAO.getDongneCommentContent((String)brid);
		
		mv.addObject("vo", vo);
		mv.setViewName("redirect:/dongneLife_content.do?bid="+bid);
		
		return mv;
	}
	@Override
	public Object getUpdateContent(Object brid, String rno ) {
		ModelAndView mv = new ModelAndView();
		DongneCommentVO vo = new DongneCommentVO();
		String bid = dongneCommentDAO.getBid(vo);
		
		vo = dongneCommentDAO.getDongneCommentContent((String)brid);
		
		mv.addObject("rno", rno);
		mv.addObject("vo", vo);
		mv.setViewName("dongneLife/dongneLifeComment_content");
		
		return mv;
	}
	
	public Object getUpdateContent(Object brid, String rno ,String bstitle ) {
		ModelAndView mv = new ModelAndView();
		DongneCommentVO vo = new DongneCommentVO();
		String bid = dongneCommentDAO.getBid(vo);
		
		vo = dongneCommentDAO.getDongneCommentContent((String)brid);
		mv.addObject("bstitle", bstitle);
		mv.addObject("rno", rno);
		mv.addObject("vo", vo);
		mv.setViewName("dongneLife/dongneLifeComment_content");
		
		return mv;
	}
	
	@Override
	public Object delete(Object brid ) {
		boolean result = false;
		String str = "";
		DongneCommentVO dcvo = new DongneCommentVO();
		dcvo.setBrid((String)brid);
		
		String bid = dongneCommentDAO.getBid(dcvo);
		result = dongneCommentDAO.dongneCommentDelete((String)brid);
		
		if(result) {
			str = "redirect:/dongneLife_content.do?bid="+bid;
		}else {
			
		}
		
		return str;
	}
	public Object delete1(Object brid ,String bstitle ) throws UnsupportedEncodingException {
		boolean result = false;
		String str = "";
		DongneCommentVO dcvo = new DongneCommentVO();
		dcvo.setBrid((String)brid);
		
		String encodedParam = URLEncoder.encode(bstitle, "UTF-8");
		result = dongneCommentDAO.dongneCommentDelete((String)brid);
		
		if(result) {
			str = "redirect:/mypage_subjectContent.do?bstitle="+encodedParam;
		}else {
			
		}
		
		return str;
	}
	

	@Override
	public Object getSelectList(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 동네생활 리스트 출력
	 */
	public String getCommentListAjaxProc(String bid) {
				
		ArrayList<DongneCommentVO> list = dongneCommentDAO.getDongneCommentList(bid); 
		int comment_count = dongneCommentDAO.getCommentCount(bid);
		
		String str ="";	
		for(DongneCommentVO vo : list) {
			
		int	date = Integer.parseInt(vo.getBrdate());
			
			if(60>date && date>0) {
				str = date +"분";
			}else if(1440 > date && date>60) {
				str = date/60 +"시간";
			}else if (1440<date) {
				str= date/60/24 + "일";
			}else {
				str="방금";
			}
			vo.setBrdate(str);
		
		}
		//list객체의 데이터를 JSON 객체로 변환작업 필요 ---> JSON 라이브러리 존재(gson)
		JsonArray jarray = new JsonArray();
		JsonObject jdata = new JsonObject();
		Gson gson = new Gson();
		
		for(DongneCommentVO vo : list){
			JsonObject jobj = new JsonObject();
			jobj.addProperty("mid", vo.getMid());  
			jobj.addProperty("nickname", vo.getNickname());
			jobj.addProperty("maddr", vo.getMaddr());
			jobj.addProperty("msfile", vo.getMsfile());
			jobj.addProperty("brid", vo.getBrid());
			jobj.addProperty("bid", vo.getBid());
			jobj.addProperty("bcomment", vo.getBcomment().replace("\r\n", "<br>"));
			jobj.addProperty("brdate", vo.getBrdate());
			jobj.addProperty("rno", vo.getRno());
			
			jarray.add(jobj);
		}
		
		jdata.add("jlist", jarray);		//java객체
		jdata.addProperty("comment_count", comment_count);
		
		return gson.toJson(jdata);
	}
	
}
