package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Balance;
import com.vtube.service.BalanceService;
import com.vtube.mapper.BalanceMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_balance(余额表)】的数据库操作Service实现
* @createDate 2024-04-29 17:02:48
*/
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance>
    implements BalanceService{

}




