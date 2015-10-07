package evo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jna.Library;
import com.sun.jna.Native;

import evo.model.Visitor;
import evo.model.VisitorRegister;
import evo.service.IVisitorRegisterService;
import evo.service.IVisitorService;
import evo.util.DateUtil;

/**
 * 项目名称：uz
 * 类名称：FingerCompare
 * 类描述：指纹比对类
 * 创建人：zoujun
 * 创建时间：2015-7-8 下午5:57:27
 * 修改人：zoujun
 * 修改时间：2015-7-8 下午5:57:27
 * 修改备注：
 * @version 
 */

/**
 * 项目名称：uz 类名称：FingerCompare 类描述： 创建人：zoujun 创建时间：2015-7-10 上午11:26:19
 * 修改人：zoujun 修改时间：2015-7-10 上午11:26:19 修改备注：
 * 
 * @version
 */
@Controller
@RequestMapping("/fingerCompareController")
public class FingerCompare {

	private static final Logger logger = Logger.getLogger(FingerCompare.class);
	@Autowired
	private IVisitorService visitorService;
	
	@Autowired
	private IVisitorRegisterService visitorRegisterService;
	
	@RequestMapping("/{fingerCode}/CompareFinger")
	@ResponseBody
	public String CompareFinger(@PathVariable String fingerCode,
			HttpServletRequest request) {
		
		JSONObject resultObject = new JSONObject();
		/**
		 * 比对指纹开始
		 * 
		 */
		logger.info("开始比对"+DateUtil.getStandardDateTime());
		List<Visitor> visitorList = visitorService.findAllVisitor();
		boolean cpmpareResult = false;
		Integer visitorId=-1;
		for(Visitor visitor:visitorList){
			if(match(fingerCode, visitor.getFinger1Path())||match(fingerCode, visitor.getFinger2Path())||match(fingerCode, visitor.getFinger3Path())
					||match(fingerCode, visitor.getFinger4Path())||match(fingerCode, visitor.getFinger5Path())||match(fingerCode, visitor.getFinger6Path())){
				cpmpareResult =true;
				visitorId = visitor.getVisitorId();
			}
		}
		logger.info("开始比对结束"+DateUtil.getStandardDateTime());
		resultObject.put("reslut",cpmpareResult);
		/**
		 * 比对指纹结束
		 * 
		 */
		/**
		 * 存入来访信息
		 * 
		 */
		if(cpmpareResult){
			VisitorRegister visitorRegister =new VisitorRegister();
			visitorRegister.setVisitorId(visitorId);
			visitorRegister.setRegisterTime(DateUtil.getStandardDateTime());
			visitorRegister.setTitie(0);
			visitorRegisterService.insertVisitorRegister(visitorRegister);
		}

		/**
		 * 返回结果给闸机
		 */
		/**/
		return JSONObject.fromObject(resultObject).toString();
	}
	
	@RequestMapping("/{fingerCode}/CompareFingerOut")
	@ResponseBody
	public String CompareFingerOut(@PathVariable String fingerCode,
			HttpServletRequest request) {
		
		JSONObject resultObject = new JSONObject();
		/**
		 * 比对指纹开始
		 * 
		 */
		logger.info("开始比对"+DateUtil.getStandardDateTime());
		List<Visitor> visitorList = visitorService.findAllVisitor();
		boolean cpmpareResult = false;
		Integer visitorId=-1;
		for(Visitor visitor:visitorList){
			if(match(fingerCode, visitor.getFinger1Path())||match(fingerCode, visitor.getFinger2Path())||match(fingerCode, visitor.getFinger3Path())
					||match(fingerCode, visitor.getFinger4Path())||match(fingerCode, visitor.getFinger5Path())||match(fingerCode, visitor.getFinger6Path())){
				cpmpareResult =true;
				visitorId = visitor.getVisitorId();
			}
		}
		logger.info("开始比对结束"+DateUtil.getStandardDateTime());
		resultObject.put("reslut",cpmpareResult);
		/**
		 * 比对指纹结束
		 * 
		 */
		/**
		 * 存入来访信息
		 * 
		 */
		if(cpmpareResult){
			VisitorRegister visitorRegister =new VisitorRegister();
			visitorRegister.setVisitorId(visitorId);
			visitorRegister.setRegisterTime(DateUtil.getStandardDateTime());
			visitorRegister.setTitie(1);
			visitorRegisterService.insertVisitorRegister(visitorRegister);
		}

		/**
		 * 返回结果给闸机
		 */
		/**/
		return JSONObject.fromObject(resultObject).toString();
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		// 请求地址
		// 指纹仪获取的指纹码 ，长度512位， 开始字符为CB
		String fingerCode = "CB091656A1AA85943B45817033AD7D705A7BF4765A77F47A94EFFC7E34E1C482A8C7990DE897572060C7EB272A6DAB1AC79AEC6A96B8CE47CA68B4BDB9FE17600E1899264FA8BE46E7C31D42F4659F7E03B09D242A1C355E8773D44275D81DD55FBFFF8B33E63D9FF4F420031A54F52FA8E4E4B06148ED674EEBD493E28B79179512F66E00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		System.out.println(fingerCode.length());
		// 请求地址(暂定 IP没定)
		/*String url = "http://192.168.1.54:8080/fingerCompareController/"
				+ fingerCode + "/CompareFinger.do";*/
		System.out.println(match(fingerCode, fingerCode));

	}

	public interface FingerMatchLibrary extends Library {
		FingerMatchLibrary FingerMatchdll = (FingerMatchLibrary) Native
				.loadLibrary("eAlgDLL", FingerMatchLibrary.class);

		public int CharMatch(byte[] Src, byte[] Dst);

	}

	/**
	 * @Title: match
	 * @Description: 指纹比对函数，当比对结果大于等于80则证明比对成功
	 * @param SrcStr
	 *            原始指纹
	 * @param Dststr
	 *            目标指纹
	 * @return Boolean
	 * @throws
	 */
	private static Boolean match(String SrcStr, String Dststr) {
		int result = 0;
		byte[] Src = new byte[512];
		Src = HexString2Bytes(SrcStr);
		byte[] Dst = new byte[512];
		if(StringUtils.isNotBlank(Dststr)){
			Dst = HexString2Bytes(Dststr);
			result = FingerMatchLibrary.FingerMatchdll.CharMatch(Src, Dst);
			if (result >= 80) {
				return true;
			} else {
				return false;
			}
		}else{
			return false;
		}

	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	private static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[256];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < 256; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	private static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

}
