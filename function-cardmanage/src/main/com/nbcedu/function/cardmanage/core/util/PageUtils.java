package com.nbcedu.function.cardmanage.core.util;

/*     */ 
/*     */ public class PageUtils
/*     */ {
/*  27 */   private int totalPage = 1;
/*     */ 
/*  32 */   private int prePage = 1;
/*     */ 
/*  37 */   private int nextPage = 1;
/*     */ 
/*  42 */   private int totalResult = 0;

   private final int defaultPageSize = 10;
 
   private int pageSize = 10;
 
/*  57 */   private int pageIndex = 1;
/*     */   private int[] pageNumbers;
/*     */ 
/*     */   public int getPageIndex()
/*     */   {
/*  70 */     return this.pageIndex;
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex)
/*     */   {
/*  79 */     this.pageIndex = (pageIndex > 0 ? pageIndex : 1);
/*     */   }
/*     */ 
/*     */   public int getNextPage()
/*     */   {
/*  88 */     return this.nextPage;
/*     */   }
/*     */ 
/*     */   public void setNextPage(int nextPage)
/*     */   {
/*  97 */     this.nextPage = (nextPage > this.totalPage ? this.totalPage : nextPage);
/*     */   }
/*     */ 
/*     */   public int getPageSize()
/*     */   {
/* 106 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize)
/*     */   {
/* 115 */     this.pageSize = (pageSize > 0 ? pageSize : 10);
/*     */   }
/*     */ 
/*     */   public int getPrePage()
/*     */   {
/* 124 */     return this.prePage;
/*     */   }
/*     */ 
/*     */   public void setPrePage(int prePage)
/*     */   {
/* 133 */     this.prePage = (prePage < 1 ? 1 : prePage);
/*     */   }
/*     */ 
/*     */   public int getTotalPage()
/*     */   {
/* 142 */     return this.totalPage;
/*     */   }
/*     */ 
/*     */   public void setTotalPage(int totalPage)
/*     */   {
/* 151 */     this.totalPage = (totalPage > 0 ? totalPage : 1);
/*     */   }
/*     */ 
/*     */   public int getTotalResult()
/*     */   {
/* 160 */     return this.totalResult;
/*     */   }
/*     */ 
/*     */   public void setTotalResult(int totalResult)
/*     */   {
/* 169 */     this.totalResult = (totalResult > -1 ? totalResult : 0);
/*     */   }
/*     */ 
/*     */   public int[] getPageNumbers()
/*     */   {
/* 178 */     return this.pageNumbers;
/*     */   }
/*     */ 
/*     */   public void setPageNumbers(int[] pageNumbers)
/*     */   {
/* 187 */     this.pageNumbers = pageNumbers;
/*     */   }
/*     */ }