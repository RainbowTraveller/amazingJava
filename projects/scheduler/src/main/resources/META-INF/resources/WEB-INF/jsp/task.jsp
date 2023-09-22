<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <html>
    <head>
      <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
      <link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
      <title>New task Details Page</title>
    </head>
    <body>
        <div class="container">
            <h1 style="border:2px solid Blue;">Add New Task Details</h1>
            <form:form method="post" modelAttribute="task">
                <fieldset class="mb-3">
                    <form:label path="description">Details</form:label>
                    <form:input type="text" path="description" required="required"/> 
                    <form:errors path="description" cssClass="txt-warning"/>
                </fieldset>

                <fieldset class="mb-3">
                    <form:label path="endDate">Target Date</form:label>
                    <form:input type="text" path="endDate" required="required"/> 
                    <form:errors path="endDate" cssClass="txt-warning"/>
                </fieldset>

                <form:input type="hidden" path="id"/>
                <form:input type="hidden" path="done"/>
                <input type="submit" class="btn btn-success"/>
            </form:form>
        </div>
      <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
      <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
      <script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
      <script type="text/javascript">
                $('#endDate').datepicker({
                format: 'yyyy-mm-dd',
                });
      </script>
    </body>
    
 </html>
