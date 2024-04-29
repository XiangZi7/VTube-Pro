package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Vip;
import com.vtube.service.VipService;
import com.vtube.mapper.VipMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_vip(会员表)】的数据库操作Service实现
* @createDate 2024-04-29 17:02:48
*/
@Service
public class VipServiceImpl extends ServiceImpl<VipMapper, Vip>
    implements VipService{

}



