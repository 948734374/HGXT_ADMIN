package com.mcfish.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.service.common.IBasicService;
import com.mcfish.util.Const;
import com.mcfish.util.DateUtil;
import com.mcfish.util.FileUpload;
import com.mcfish.util.Logger;
import com.mcfish.util.PageData;
import com.mcfish.util.PathUtil;


@Controller
@RequestMapping(value = "/shareBasicController")
public class BasicController extends BaseController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource(name = "basicServiceImpl")
	private IBasicService basicServiceImpl;
	
	
	/**
	 * 访问后台主页
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午1:47:42 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toWelcomePage.do")
	public ModelAndView toWelcomePage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		mv.setViewName("welcome");
		mv.addObject("pd", pd);
		
		return mv;
	}

	@RequestMapping(value = "/fileUpload")
	public String fileUpload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logBefore(logger, "++++++++++ 上传图片  ++++++++++");
		Map<String, Object> data = new HashMap<String, Object>();
		String time = DateUtil.getTime().substring(11, 13).replace(":", "");
		String ffile = DateUtil.getDays() + "/" + time, fileName = "";
		String path = null;
		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = null;

		// 设置响应给前台内容的PrintWriter对象
		PrintWriter out = response.getWriter();
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input
		// type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		for (MultipartFile myfile : myfiles) {
			if (null == myfile || myfile.isEmpty()) {
				logger.error("++++++++++ 上传图片【失败】，上传的图片不正确  ++++++++++");
				out.print("1`请选择文件后上传");
				out.flush();
				return null;
			} else {
				originalFilename = myfile.getOriginalFilename();
				logger.info("++++++++++ 文件原名：" + originalFilename);
				logger.info("++++++++++ 文件名称：" + myfile.getName());
				logger.info("++++++++++ 文件长度：" + myfile.getSize());
				logger.info("++++++++++ 文件类型：" + myfile.getContentType());
				logger.info("========================================");
				try {
					// 文件上传路径 和项目在同一级目录
					String filePath = PathUtil.getClasspath() + "../" + Const.FILEPATHIMG + ffile;
					fileName = FileUpload.fileUp(myfile, filePath, this.get32UUID()); // 执行上传
					path = "/" + Const.FILEPATHIMG + ffile + "/" + fileName;
					File up = new File(filePath);
					if (!up.exists()) {
						up.mkdirs();
					}
					logger.info("++++++++++ 图片存储的相对路径：" + path);
					data.put("path", path);
				} catch (Exception e) {
					e.printStackTrace();
					out.print("1`文件上传失败，请重试！！");
					out.flush();
					return null;
				}
			}
		}
		logAfter(logger);
		out.print("0`" + path);
		out.flush();
		return null;
	}

	// 上传图片
	@ResponseBody
	@RequestMapping(value = "/uploadPicture")
	public Object uploadPicture(@RequestParam(required = false) MultipartFile file,
			@RequestParam(required = false) String token) throws Exception {
		logBefore(logger, "++++++++++ 上传图片  ++++++++++");
		Map<String, Object> data = new HashMap<String, Object>();
		String time = DateUtil.getTime().substring(11, 13).replace(":", "");
		String ffile = DateUtil.getDays() + "/" + time, fileName = "";
		try {

			if (null != file && !file.isEmpty()) {
				// 文件上传路径 和项目在同一级目录
				String filePath = PathUtil.getClasspath() + "../" + Const.FILEPATHIMG + ffile;
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传

				String path = "/" + Const.FILEPATHIMG + ffile + "/" + fileName;
				logger.info("++++++++++ 图片存储的相对路径：" + path);

				data.put("path", path);
			} else {
				logger.error("++++++++++ 上传图片【失败】，上传的图片不正确  ++++++++++");
				return InterfaceResult.returnResult(3, "您上传的图片不正确！", data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return InterfaceResult.returnFailure();
		}
		logAfter(logger);
		return InterfaceResult.returnSuccess(data);
	}

	/**
	 * 文件上传 后台使用
	 * 
	 * @author HongZuRen 2017年7月30日 下午8:30:42
	 * @version Mcfish 1.0
	 * @param myfiles
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/fileUpload2")
	public Object fileUpload2(@RequestParam MultipartFile[] myfiles, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logBefore(logger, "++++++++++ 上传文件  ++++++++++");
		// 处理中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		Map<String, Object> map = new HashMap<String, Object>();

		String time = DateUtil.getHHmmss();
		String ffile = DateUtil.getDays() + "/" + time, fileName = "";
		String path = null;
		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		String filePath = null;

		// 设置响应给前台内容的PrintWriter对象
		// PrintWriter out = response.getWriter();
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input
		// type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		for (MultipartFile myfile : myfiles) {
			if (null == myfile || myfile.isEmpty()) {
				logger.error("++++++++++ 上传文件【失败】，上传的文件不正确  ++++++++++");
				map.put("msg", "上传文件【失败】，上传的文件大小为0或上传的文件不正确");
				map.put("status", 0);
				return map;
			} else {
				originalFilename = myfile.getOriginalFilename();
				logger.info("++++++++++ 文件原名：" + originalFilename);
				logger.info("++++++++++ 文件名称：" + myfile.getName());
				logger.info("++++++++++ 文件长度：" + myfile.getSize());
				logger.info("++++++++++ 文件类型：" + myfile.getContentType());
				logger.info("========================================");
				try {
					// 文件上传路径 和项目在同一级目录
					filePath = PathUtil.getClasspath() + "../" + Const.FILEPATHFILE + ffile;
					fileName = FileUpload.fileUp(myfile, filePath, myfile.getOriginalFilename()
							.substring(0, myfile.getOriginalFilename().lastIndexOf(".")).replace(";", "-")); // 执行上传
					path = "/" + Const.FILEPATHFILE + ffile + "/" + fileName;
					logger.info("++++++++++ 文件存储的相对路径：" + path);
					System.out.println("上传路径+" + filePath);
					System.out.println(path);
					map.put("fileName", originalFilename);
					map.put("filePath", filePath);
					map.put("status", 1);
				} catch (Exception e) {
					e.printStackTrace();
					map.put("msg", "文件上传失败,请联系管理员");
					map.put("status", 0);
					return map;
				}
			}
		}
		logAfter(logger);
		return map;
	}

	/**
	 * 文件上传3，上传文件至webapp/dist/appversion
	 * 
	 * @author ZhouXiaoBing
	 * @date 2017年11月6日 上午9:11:09
	 * @param myfiles
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/fileUpload3")
	public Object fileUpload3(@RequestParam MultipartFile[] myfiles, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("++++++++++++++++++++++++++++++ 上传文件  ++++++++++++++++++++++++++++++");
		// 处理中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		Map<String, Object> map = new HashMap<String, Object>();

		String time = DateUtil.getHHmmss();
		String ffile = DateUtil.getDays() + "/" + time, fileName = "";
		String path = null;
		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		String filePath = null;

		for (MultipartFile myfile : myfiles) {
			if (null == myfile || myfile.isEmpty()) {
				logger.error("++++++++++++++++++++++++++++++ 上传文件【失败】，上传的文件不正确  ++++++++++++++++++++++++++++++");
				return InterfaceResult.returnFailureWithMsg("上传文件【失败】，上传的文件大小为0或上传的文件不正确");
			} else {
				originalFilename = myfile.getOriginalFilename();
				logger.info("---------- 文件原名：" + originalFilename);
				logger.info("---------- 文件名称：" + myfile.getName());
				logger.info("---------- 文件长度：" + myfile.getSize());
				logger.info("---------- 文件类型：" + myfile.getContentType());
				logger.info("==============================================================================");
				try {
					// 文件上传路径 和项目在同一级目录
					filePath = PathUtil.getClasspath() + Const.FILEPATHFILE + ffile;
					fileName = FileUpload.fileUp(myfile, filePath, myfile.getOriginalFilename()
							.substring(0, myfile.getOriginalFilename().lastIndexOf(".")).replace(";", "-")); // 执行上传
					path = Const.FILEPATHFILE + ffile + "/" + fileName;
					logger.info("---------- 文件存储的相对路径：" + path);
					logger.info("-----------------上传路径：" + filePath);
					map.put("fileName", originalFilename);
					map.put("filePath", path);
				} catch (Exception e) {
					e.printStackTrace();
					return InterfaceResult.returnFailureWithMsg("文件上传失败,请联系管理员");
				}
			}
		}
		return InterfaceResult.returnSuccess(map);
	}

}
