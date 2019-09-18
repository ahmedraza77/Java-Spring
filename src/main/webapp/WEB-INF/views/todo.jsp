<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

	<div class="container">
		<form:form method="post" modelAttribute="todo">
		      <form:hidden path="id"/>  
		   
			<fieldset class="form-group">
				<form:label path="desc">Description</form:label>
				<form:input path="desc" type="text" class="form-control"
					required="required"/>
				<form:errors path="desc" cssClass="text-warning" />

			</fieldset>
			<input class="btn btn-success" type="submit" value="Submit" />
		</form:form>
	</div>
	
<%@ include file="common/footer.jspf"%>
	