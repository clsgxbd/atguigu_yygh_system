package com.atguigu.yygh.hosp.controller;


import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 医院设置表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-10-17
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
//@CrossOrigin //跨域
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation("分页及带条件查询")
    @PostMapping("/findHospSetPage/{current}/{limit}")
    public R findHospSetPage(
            @ApiParam("当前页码")
            @PathVariable(value = "current")long current,
            @ApiParam("当前页码")
            @PathVariable(value = "limit")long limit,
            @ApiParam(value = "查询条件",required = false)
            @RequestBody HospitalSetQueryVo hospitalSetQueryVo
    ) {

        Page<HospitalSet> page = new Page<>(current, limit);
        if (hospitalSetQueryVo == null) {
            hospitalSetService.page(page);
        } else {
            String hoscode = hospitalSetQueryVo.getHoscode();
            String hosname = hospitalSetQueryVo.getHosname();
            QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(hoscode)) {
                wrapper.like("hoscode", hoscode);
            }
            if (!StringUtils.isEmpty(hosname)) {
                wrapper.like("hosname", hosname);
            }
            wrapper.orderByDesc("update_time"); // 根据修改时间倒序排序
            hospitalSetService.page(page,wrapper);
        }

        List<HospitalSet> list = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list", list).data("total", total);
    }

    @ApiOperation("查询所有")
    @GetMapping("/findAllHospSet")
    public R findAllHospSet(){
        List<HospitalSet> list = hospitalSetService.list();
        return R.ok().data("list",list);
    }

    @ApiOperation("通过id查询")
    @GetMapping("/findHospSetById/{id}")
    public R findHospSetById(@PathVariable long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return R.ok().data("hospitalSet", hospitalSet);
    }

    @ApiOperation("添加")
    @PostMapping("/saveHospSet")
    public R saveHospSet(@RequestBody HospitalSet hospitalSet){
        boolean is_success = hospitalSetService.save(hospitalSet);
        if (is_success) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("通过id修改")
    @PutMapping("/updateHospSetById")
    public R updateHospSetById(@RequestBody HospitalSet hospitalSet){
        boolean is_success = hospitalSetService.updateById(hospitalSet);
        if (is_success) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @ApiOperation("通过id删除")
    @DeleteMapping("/deleteHospSetById/{id}")
    public R deleteHospSetById(@PathVariable long id){
        boolean is_success = hospitalSetService.removeById(id);
        if (is_success) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @ApiOperation("批量删除")
    @DeleteMapping("/deleteBatch")
    public R deleteBatch(@RequestBody List<Long> idList){
//        // 模拟异常
//        try {
//            int i = 1 / 0;
//        } catch (Exception e) {
//            throw new YyghException(110, "预约挂号异常");
//        }

        boolean is_success = hospitalSetService.removeByIds(idList);
        if (is_success) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("医院锁定解锁")
    @PutMapping("/lockHospitalSet/{id}/{status}")
    public R lockHospitalSet(@PathVariable long id , @PathVariable Integer status ){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        hospitalSetService.updateById(hospitalSet);
        return R.ok();
    }
}

