<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <html>
    <head>
      <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
      <title>Task List Page</title>
    </head>
    <body>
        <div class="container">
            <h1 style="border:2px solid Blue;">Welcome To List of the Tasks</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td>${task.id}</td>
                            <td>${task.description}</td>
                            <td>${task.endDate}</td>
                            <td>${task.done}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="addTask" class="btn btn-success">Add Task</a>
        </div>
      <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
      <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    </body>
    
 </html>
