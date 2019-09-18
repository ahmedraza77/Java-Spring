<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

	<h2>HI ${name}</h2>
	
	<div class="container">
		<table class="table table-striped">
			<caption><spring:message code="todo.caption"/></caption>

			<thead>
				<tr>
					<th>Description</th>
					<th>Date</th>
					<th>Completed</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="/update-todos?id=${todo.id}" class="btn btn-success">Update</a></td>    <!--  passing id the url -->
						<td><a href="/delete-todos?id=${todo.id}" class="btn btn-danger">Delete</a></td>    <!--  passing id the url -->
					
					</tr>
				</c:forEach>
			</tbody>
		</table>

	<div>
	<a class="btn btn-success" href="/add-todos">Add</a>
	</div>
	</div>
 
<%@ include file="common/footer.jspf"%>
 