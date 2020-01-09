package top.yoga.lol.common.page;

import java.util.List;

/**
 * 分页工具
 *
 * @author luojiayu
 * @date 2020/1/9 15:21
 */
public class PageUtils {
    /**
     * 默认分页大小
     */
    private static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 当前页
     */
    private Integer pageNum = 1;
    /**
     * 每页显示数据条数
     */
    private Integer pageSize = 10;
    /**
     * 所有记录数
     */
    private int totalRows;
    /**
     * sql查询起始行
     */
    private Integer startRow;
    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 减少的查询行数量
     */
    private Integer subRows;

//    public static PageUtils getPage(){
//
//    }
}
