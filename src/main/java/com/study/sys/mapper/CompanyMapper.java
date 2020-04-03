package com.study.sys.mapper;

import com.study.sys.entity.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 公司信息 Mapper 接口
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

}
