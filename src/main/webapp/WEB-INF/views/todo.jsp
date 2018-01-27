<%@ include file="common/headerDatePicker.jsp"%>
<%@ include file="common/navigation.jsp"%>



<div class="container">
	<h1>Add todo</h1>

	<form:form method="post" commandName="todo">
		<fieldset class="form-group">
			<form:label path="desc">Description</form:label>
			<form:input path="desc" name="desc" type="text" class="form-control"
				required="required" />
			<form:errors path="desc" cssClass="text-warning" />
			<form:label path="user">User</form:label>
			<form:input path="user" name="user" type="text" class="form-control" />
			<form:errors path="done" cssClass="text-warning" />
			<form:label path="done">Done</form:label>
			<form:input path="done" name="done" type="text" class="form-control"
				required="required" />
			<form:errors path="done" cssClass="text-warning" />


			<form:label path="targetDate">Pick date and time</form:label>
			<form:input path="targetDate" name="targetDate" type="datetime-local"
				class="form-control" />


		</fieldset>
		<input class="btn btn-success" type="submit" value="Apply changes">
	</form:form>
</div>
<%@ include file="common/footerDatePicker.jsp"%>