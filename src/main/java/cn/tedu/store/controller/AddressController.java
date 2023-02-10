package cn.tedu.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.tedu.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

    @Autowired 
    private IAddressService addressService;

	@Autowired
	private IDistrictService iDistrictService;

    @RequestMapping("addnew")
	public JsonResult<Void> addnew(
		Address address, HttpSession session) {
		// 从Session中获取uid和username
    	Integer uid = getUidFromSession(session);
    	String username = getUsernameFromSession(session);
		// 调用业务层对象执行增加
    	addressService.addnew(address, uid, username);
		// 响应成功
    	return new JsonResult<>(SUCCESS);
	}

	@RequestMapping("{aid}/updAdd")
	public JsonResult<Void> updAdd(@PathVariable("aid") Integer aid,
			Address address, HttpSession session) {
		// 从Session中获取uid和username
		String username = getUsernameFromSession(session);
		// 调用业务层对象执行增加
		addressService.updAdd(address, aid, username);
		// 响应成功
		return new JsonResult<>(SUCCESS);
	}
    
    @GetMapping("/")
    public JsonResult<List<Address>> getByUid(
    		HttpSession session) {
    	// 从session中获取uid
    	Integer uid = getUidFromSession(session);
    	// 调用业务层对象获取数据
    	List<Address> data
    		= addressService.getByUid(uid);
    	// 响应
    	return new JsonResult<>(SUCCESS, data);
    }

    @PostMapping("{aid}/getAddressByAid")
    public JsonResult<Map> getAddressByAid(@PathVariable("aid") Integer aid, HttpSession session){
//    	获取收货地址的aid
//		Integer aid = getAidFromSession(session);
//		查询数据
		Map map = new HashMap<>();
		Address byAid = addressService.getByAid(aid);
//		 根据父类code查询全部地区数据
		Map allData = iDistrictService.getAllData(byAid.getProvinceCode(), byAid.getCityCode());

		map.put("aid",byAid);
		map.put("list",allData);
		return new JsonResult<>(SUCCESS, map);
	}
    
    @RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(
		@PathVariable("aid") Integer aid,
		HttpSession session) {
    	// 从Session中获取uid和username
    	Integer uid = getUidFromSession(session);
    	String username = getUsernameFromSession(session);
		// 调用业务层对象执行设置默认
    	addressService.setDefault(aid, uid, username);
		// 响应成功
    	return new JsonResult<>(SUCCESS); 
	}
    
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(
    		@PathVariable("aid") Integer aid,
    		HttpSession session) {
    	// 从Session中获取uid和username
    	Integer uid = getUidFromSession(session);
    	String username = getUsernameFromSession(session);
    	// 调用业务层对象执行设置默认
    	addressService.delete(aid, uid, username);
    	// 响应成功
    	return new JsonResult<>(SUCCESS); 
    }
    
}








