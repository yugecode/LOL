package top.yoga.lol.common.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 分页查询bean
 *
 * @author luojiayu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageQueryBean<T> {
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
     * 查询所得数据集
     */
    private List<T> items;

    /**
     * 减少的查询行数量
     */
    private Integer subRows;

    public final Integer getStartRow() {
        if (startRow == null) {
            startRow = (pageNum == null || pageNum == 0 ? 0 : (pageNum - 1) * getPageSize()) - (subRows == null ? 0 :
                    subRows);
        }
        return startRow;
    }


    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }


    public final Integer getPageSize() {
        return (pageSize == null || pageSize == 0) ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public final void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public final int getTotalRows() {
        return totalRows;
    }

    public final void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        int totalPage = totalRows % getPageSize() == 0 ? totalRows / getPageSize() : totalRows / getPageSize() + 1;
        setTotalPage(totalPage);
    }

    public final List<T> getItems() {
        return items == null ? Collections.EMPTY_LIST : items;
    }

    public final void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public final Integer getTotalPage() {
        return totalPage == null || totalPage == 0 ? 1 : totalPage;
    }

    public final void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getSubRows() {
        return subRows;
    }

    public void setSubRows(Integer subRows) {
        this.subRows = subRows;
    }

    @Override
    public String toString() {
        return "PageQueryBean [pageNum=" + pageNum + ", pageSize="
                + pageSize + ", totalRows=" + totalRows + ", startRow="
                + startRow + ", totalPage=" + totalPage + ", items=" + items
                + ", subRows=" + subRows + "]";
    }

}
