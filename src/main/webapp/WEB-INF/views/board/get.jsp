<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %> 
<!-- JSP include -->
<%-- <%@ include file="../includes/header.jsp" %> --%>
<!-- JSP 액션태그 include -->
<jsp:include page="../includes/header.jsp"></jsp:include>

			<div class="col-md-12">
				<h4 class="m-b-lg">Board Read Page</h4>
			</div><!-- END column -->


			<div class="col-md-12">
				<div class="widget p-lg">
					<h4 class="m-b-lg">Board Read Page</h4>
					<p class="m-b-lg docs">
						<!-- Add <code>.table-hover</code> to enable a hover state on table rows within a <code>&lt;tbody&gt;</code>. -->
					</p>

					<div class="panel-body">
						<form id="frm" method="post" action="" enctype="multipart/form-data">
						<input type="hidden" name="bno" id="bno" value="${board.bno}">
							<div class="form-group">
								<label>제목</label><input type="text" name="title" id="title" class="form-control" value="${board.title}" required="required">
							</div>
							<div class="form-group">
								<label>내용</label><textarea name="content" id="content" class="form-control" style="resize:none;" required="required">${board.content}</textarea>
								<c:set value="-1" var="cnt"></c:set>
								<c:forEach items="${board.attachList}" var="attach" varStatus="status">
									<input type="hidden" name="oldfile" value="${attach.fileName}">
									<c:set value="${status.count}" var="cnt"></c:set>
									<a href="/download?fileName=${attach.fileName}"><img src="display?fileName=sm_${attach.fileName}" alt="첨부이미지"></a>
								</c:forEach>
								<c:if test="${cnt == -1}">
									<input type="hidden" name="oldfile" value="">
								</c:if>
							</div>
							<div class="form-group">
								<label>첨부파일</label><input type="file" name="upfile" id="upfile" multiple="multiple" class="form-control" value="">
							</div>
							<div class="form-group">
								<label>작성자</label><input type="text" name="writer" id="writer" class="form-control" value="${board.writer}" required="required">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-default">글수정</button>
								<button type="button" id="btn_del" class="btn btn-default">삭제</button>
							</div>
						</form>
					</div>
				</div><!-- .widget -->
			</div><!-- END column -->

<script>
$(document).ready(function(){
	$("#btn_del").on("click",function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			//GET 방식: location.href='remove?bno=${board.bno}';
			//POST 방식
			$("#frm").attr("action","remove");
			$("#frm").submit();
		}
	});
	
	var str = $('#content').val();
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	$('#content').val(str);
	
/* 	function XSSCheck(str, level) {
		if (level == undefined || level == 0) {        
			str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g,"");    
		} else if (level != undefined && level == 1) {
			str = str.replace(/\</g, "&lt;");        
			str = str.replace(/\>/g, "&gt;");    
		}    
		return str;
	} */
});

</script>

<!-- JSP 액션태그 include -->
<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- JSP include -->
<%-- <%@ include file="../includes/footer.jsp" %> --%>



