package cn.tedu.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        return findByParent(parent);
    }

    @Override
    public District getByCode(String code) {
        return findByCode(code);
    }

    @Override
    public Map getAllData(String provinceCode, String cityCode) {
        List<District> provinceList = districtMapper.findByParent("86");
        List<District> cityList = districtMapper.findByParent(provinceCode);
        List<District> areaList = districtMapper.findByParent(cityCode);
        Map<String, List<District>> map = new HashMap<String, List<District>>();
        map.put("provinceList", provinceList);
        map.put("cityList", cityList);
        map.put("areaList", areaList);
        return map;
    }

    /**
     * 根据父级代号获取全国所有省/某省所有市/某市所有区的列表
     *
     * @param parent 父级代号，如果尝试获取全国所有省，则代号应该使用"86"
     * @return 全国所有省/某省所有市/某市所有区的列表
     */
    private List<District> findByParent(String parent) {
        return districtMapper.findByParent(parent);
    }

    /**
     * 根据省/市/区的代号查询详情
     *
     * @param code 省/市/区的代号
     * @return 匹配的省/市/区的详情，如果没有匹配的数据，则返回null
     */
    private District findByCode(String code) {
        return districtMapper.findByCode(code);
    }


}





