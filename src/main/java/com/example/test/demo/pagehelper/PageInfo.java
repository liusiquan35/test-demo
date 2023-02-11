package com.example.test.demo.pagehelper;

public class PageInfo {
    private int pageNum;//当前页码
    private int pageSize;//设置每页多少条数据
    private int size;//当前页有多少条数据
    private int startRow;//当前页码第一条数据的
    private int endRow;//当前页码的开始条
    private int pages;//当前页码结束条
    private int prePage;//上一页（页面链接使用）
    private int nextPage;//下一页（页面链接使用）
    private boolean isFirstPage;//是否为第一页
    private boolean isLastPage;//是否为最后一页
    private boolean hasPreviousPage;//是否有前一页
    private boolean hasNextPage;//是否有下一页
    private int navigatePages;//导航页码数(就是总共有多少页)
    private int[] navigatepageNums;//导航页码数(就是总共有多少页),可以用来遍历
    private int navigateFirstPage;//首页号
    private int navigateLastPage;//尾页号

}
