package com.kb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kb.domain.BoardVO;
import com.kb.domain.AttachFileDTO;
import com.kb.domain.BoardAttachVO;
import com.kb.domain.BoardCriteria;
import com.kb.domain.BoardPageDTO;
import com.kb.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	

//	@GetMapping("list")
//	public void list(Model model) {
//		log.info("목록");		
//		model.addAttribute("list", service.getList());
//	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(BoardCriteria cri, Model model) {
		log.info(cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", new BoardPageDTO(service.getListWithCnt(cri), cri));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {

	}
	
	/**
	 * 이미지 파일이면 true 그렇지 않으면 false
	 * @param file
	 * @return
	 */
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			if(contentType != null) {
				return contentType.startsWith("image");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MultipartFile[] upfile, BoardVO board) {
		
		String filePath = "d:/upload";
			
		List<BoardAttachVO> list = new ArrayList<BoardAttachVO>();
		
		
		for(MultipartFile multi : upfile) {
			
			
			BoardAttachVO attachVO = new BoardAttachVO();

			String upfileTmp = multi.getOriginalFilename();
			
			
			String fileName = upfileTmp.substring(upfileTmp.lastIndexOf("/")+1);
			
			UUID uuid = UUID.randomUUID();
			
			String realSaveFileName = uuid.toString()+"_"+fileName;
			
			
			File saveFile = new File(filePath, realSaveFileName);
			try {
				multi.transferTo(saveFile);
				
				//이미지 파일이면 썸네일을 만든다.
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail 
						= new FileOutputStream(new File(filePath, "sm_" + realSaveFileName));
					Thumbnailator.createThumbnail(multi.getInputStream(), thumbnail, 256, 256);
					thumbnail.close();
				}
				
				attachVO.setUuid(uuid.toString());
				attachVO.setUploadPath(filePath);
				attachVO.setFileName(realSaveFileName);
				
				list.add(attachVO);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		service.register(board);
		
		return "redirect:/board/list";
	}
//	public String register(BoardVO board, RedirectAttributes rttr) {
//
//		service.register(board);
//		
//		return "redirect:/board/list";
//	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	//public void get(int bno) {
	public void get(@RequestParam("bno") int bno, Model model) {
		
		model.addAttribute("board", service.get(bno));

	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	//public void get(int bno) {
	public String get(MultipartFile[] upfile, BoardVO board) {
		
		log.info("upfile여부"+upfile.length);
		
		String filePath = "d:/upload";
		
		for(MultipartFile multi : upfile) {

			String upfileTmp = multi.getOriginalFilename();
			
			
			String fileName = upfileTmp.substring(upfileTmp.lastIndexOf("/")+1);
			
			UUID uuid = UUID.randomUUID();
			
			String realSaveFileName = uuid.toString()+"_"+fileName;
			
			
			File saveFile = new File(filePath, realSaveFileName);
			try {
				multi.transferTo(saveFile);
				
				//이미지 파일이면 썸네일을 만든다.
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail 
						= new FileOutputStream(new File(filePath, "sm_" + realSaveFileName));
					Thumbnailator.createThumbnail(multi.getInputStream(), thumbnail, 256, 256);
					thumbnail.close();
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		boolean result = service.modify(board);
		if(result) {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/get?bno="+board.getBno();
		}

	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	//public void get(int bno) {
	public String remove(@RequestParam("bno") int bno) {
		
		service.remove(bno);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(BoardVO board) {
		
		service.remove(board.getBno());
		
		return "redirect:/board/list";
	}
	
	/**
	 * 첨부된 파일을 보여주기위함
	 * url경로에 localhost/board/display?fileName=skfsdlfj.jpg
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		File file = new File("d:/upload/"+fileName);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}





