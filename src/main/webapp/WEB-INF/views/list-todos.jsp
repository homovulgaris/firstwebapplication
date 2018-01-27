<%@ include file="common/header.jsp"%>

<%@ include file="common/navigation.jsp"%>

<div class="container">


	<table class="table table-striped">
		<caption>Hello ${name}. ${quantity} todos are</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Completed</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<td>${todo.showFormatedDateTime()}</td>
					<td>${todo.done}</td>
					<td><a class="btn btn-warning" href="update-todo?id=${todo.id}">Update</a></td>
					<td><a class="btn btn-danger" href="delete-todo?id=${todo.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<br>
	<div>
		<a class="btn btn-success" href="add-todo">Add todo</a>

	</div>
</div>
<%@ include file="common/footer.jsp"%>