package cn.youai.ssm.dao;

import cn.youai.ssm.po.Item;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ItemMapper {
  
    Item selectByPrimaryKey(Integer refid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbggenerated
     */
    List<Item> selectAll();

}