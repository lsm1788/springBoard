<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %> 
<!-- JSP include -->
<%-- <%@ include file="../includes/header.jsp" %> --%>
<!-- JSP 액션태그 include -->
<jsp:include page="../includes/header.jsp"></jsp:include>

			<div class="col-md-12">
				<h4 class="m-b-lg">Member Register</h4>
			</div><!-- END column -->


			<div class="col-md-12">
				<div class="widget p-lg">
					<h4 class="m-b-lg">Member Register</h4>
					<p class="m-b-lg docs">
						<!-- Add <code>.table-hover</code> to enable a hover state on table rows within a <code>&lt;tbody&gt;</code>. -->
					</p>

					<div class="panel-body">
						<form method="post" action="" id="tmpSendFrm">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="authority" value="ROLE_MEMBER"/>
							<div class="form-group">
								<label>회원명</label><input type="text" name="uname" id="uname" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>학교명</label><input type="text" name="schoolname" id="schoolname" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>학년/반</label><input type="text" name="gradeclass" id="gradeclass" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>아이디</label><input type="text" name="uid" id="uid" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>비밀번호</label><input type="text" name="upw" id="upw" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>노선</label><input type="text" name="route" id="route" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>탑승장소</label><input type="text" name="boardingplace" id="boardingplace" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<button type="submit" id="submit" class="btn btn-default">회원가입</button>
								<button type="button" class="btn btn-default">취소</button>
							</div>
						</form>
					</div>
				</div><!-- .widget -->
			</div><!-- END column -->
<script>
$(document).ready(function(){
	$("#submit").on("click",function(e){
		e.preventDefault();

		let uname = $("#uname").val();
		let schoolname = $("#schoolname").val();
		let gradeclass = $("#gradeclass").val();
		let uid = $("#uid").val();
		let upw = $("#upw").val();
		let route = $("#route").val();
		let boardingplace = $("#boardingplace").val();
		
		$.ajax({
			type: "POST",
			url: '/data/members/new',	// form을 전송할 실제 파일경로
			data: JSON.stringify({uname:uname, schoolname:schoolname, gradeclass:gradeclass, 
				uid:uid, upw:upw, route:route, boardingplace:boardingplace}),
			contentType:"application/json; charset=utf-8",
			beforeSend : function() {
				// 전송 전 실행 코드
			},
			success: function (data) {
				location.href="/member/list";
			},
			error: function (e) {
				// 전송 후 에러 발생 시 실행 코드
				console.log("ERROR : ", e);
			}
		});
	});
});
	
</script>
<!-- JSP 액션태그 include -->
<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- JSP include -->
<%-- <%@ include file="../includes/footer.jsp" %> --%>



