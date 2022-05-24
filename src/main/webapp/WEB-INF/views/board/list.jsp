<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %> 
<!-- JSP include -->
<%-- <%@ include file="../includes/header.jsp" %> --%>
<!-- JSP 액션태그 include -->
<jsp:include page="../includes/header.jsp"></jsp:include>

			<div class="col-md-12">
				<h4 class="m-b-lg">Tables</h4>
			</div><!-- END column -->


			<div class="col-md-12">
				<div class="widget p-lg">
					<h4 style="display:inline;" class="m-b-lg">Board List Page</h4>
					<a href="register" style="float:right;" class="btn btn-success" role="button">Register New Board</a>
					<p class="m-b-lg docs">
						<!-- Add <code>.table-hover</code> to enable a hover state on table rows within a <code>&lt;tbody&gt;</code>. -->
					</p>

					<table class="table table-hover">
						<thead>
							<tr><th>#번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>수정일</th></tr>
						</thead>
						<c:forEach items="${list}" var="board">
						<tr>
							<td>${board.bno}</td><td><a href="get?bno=${board.bno}">${board.title}</a></td><td>${board.writer}</td><td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.regdate}"/></td><td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.updatedate}"/> </td>
						</tr>
						</c:forEach>
					</table>
					
					
					<nav style="text-align:right;">
					  <ul class="pagination">
					  	<c:if test="${pageMaker.prev}">
					    <li class="paginate_button">
					      <a href="${pageMaker.startPage - 1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
					    <li class="paginate_button">
					    	<a href="${num}">${num}</a>
					    </li>
					    </c:forEach>
					    <c:if test="${pageMaker.next}">
					    <li class="paginate_button">
					      <a href="${pageMaker.endPage + 1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    </c:if>
					  </ul>
					</nav>
					
				</div><!-- .widget -->
			</div><!-- END column -->
<form id="operForm" action="" method="get">
<input type="hidden" id="pageNum" name="pageNum" value="${pageMaker.cri.pageNum}">
<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
</form>
<script>
	$(document).ready(function(){
		$(".paginate_button a").on("click",function(e){
			e.preventDefault();
			$("#pageNum").val($(this).attr("href"));
			$("#operForm").submit();
		});
	});
</script>

<!-- JSP 액션태그 include -->
<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- JSP include -->
<%-- <%@ include file="../includes/footer.jsp" %> --%>



