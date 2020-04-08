package top.yoga.lol.information.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author luojiayu
 * @date 2020/3/2 17:21
 */
@Mapper
public interface ItemsDao {
    int insertItems(String message);

    String getItems();
}
